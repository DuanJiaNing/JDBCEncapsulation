package com.yccz.jdbcencapsulation.db;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yccz.jdbcencapsulation.FieldToken;
import com.yccz.jdbcencapsulation.TableToken;
import com.yccz.jdbcencapsulation.Token;

/**
 * ���ݿ⣬�ڲ���ʹ��ʱҪ{@code #close()}�����ر����ݿ�����
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DataBase implements Query {

	// ���ݿ�����
	private final Connection conn;

	public DataBase(Connection connection) {
		this.conn = connection;
	}

	/**
	 * ���� sql ����ѯ��������
	 * 
	 * @param clasz
	 *            Ҫ��ѯ�ı��Ӧ��ʵ������������
	 * @param sql
	 *            sql ���
	 * @return ��ѯ���
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] query(Class<T> clasz, String sql) {
		if (clasz == null || !Utils.isReal(sql)) {
			return null;
		}

		PreparedStatement stat = null;
		ResultSet set = null;
		List<T> result = null;

		try {
			// ʹ������ɹ����������ȡ�������С
			stat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			set = stat.executeQuery();

			Utils.log(sql);

			// ��ȡ��С
			int size = getResultSetSize(set);
			if (size <= 0) {
				return null;
			}

			// ��ȡӳ���ϵ
			Token<List<FieldToken.FieldHolder>> token = new FieldToken<>(clasz);
			List<FieldToken.FieldHolder> fh = token.get();
			result = new ArrayList<>();

			// ���÷��佫��ѯ�������Ŀ������
			while (set.next()) {
				T item = clasz.newInstance();
				for (FieldToken.FieldHolder h : fh) {
					Field field = h.field;
					String label = h.name;
					Class<?> type = field.getType();

					Object value = set.getObject(label, type);
					field.setAccessible(true);
					field.set(item, value);
				}
				result.add(item);
			}

		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			Utils.closeAutoCloseable(set);
			Utils.closeAutoCloseable(stat);
		}

		T[] res = null;
		if (result != null && result.size() > 0) {
			res = (T[]) Array.newInstance(clasz, result.size());
			for (int i = 0; i < result.size(); i++) {
				res[i] = result.get(i);
			}
		}
		return res;
	}

	/**
	 * ����������ѯ��������
	 * 
	 * @param clasz
	 *            Ҫ��ѯ�ı��Ӧ��ʵ������������
	 * @param whereCase
	 *            sql ��� where ��Ӧ����
	 * @param whereValues
	 *            whereCase ��Ӧ��ֵ
	 * @return ��ѯ���
	 */
	public <T> T[] query(Class<T> clasz, String[] whereCase, String[] whereValues) {
		if (clasz == null) {
			return null;
		}

		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String where = constructConditions(whereCase, whereValues);
			String sql = "select * from " + table + (where == null ? "" : " where" + where);
			return query(clasz, sql);
		} else {
			return null;
		}
	}
	
	@Override
	public <T> T[] query(Class<T> clasz) {
		if (clasz == null) {
			return null;
		}

		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String sql = "select * from " + table;
			return query(clasz, sql);
		} else {
			return null;
		}
	}

	// ����ʵ����������Ϣ������Ӧ�����ݱ�����
	private <T> String getTableName(Class<T> clasz) {
		Token<String> token = new TableToken<>(clasz);
		return token.get();
	}
	
	// ��ȡ�������С������ǰ��Ϊ prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
	// ResultSet.CONCUR_READ_ONLY)
	private int getResultSetSize(ResultSet set) throws SQLException {
		int size = -1;
		if (set != null) {
			set.last();
			size = set.getRow();
			set.beforeFirst();
		}
		return size;
	}

	// ���� sql ��� where ����
	private String constructConditions(String[] whereCase, String[] whereValues) {
		if (whereCase == null || whereCase.length == 0 || whereValues == null || whereValues.length == 0
				|| whereCase.length != whereValues.length) {
			return null;
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < whereCase.length; i++) {
			String where = whereCase[i];
			String value = whereValues[i];
			if (i > 0) {
				builder.append(" and");
			}
			builder.append(' ').append(where).append(" like ").append('\'').append(value).append('\'');
		}

		return builder.toString();
	}

	/**
	 * �����߿��Լ��ر����ݿ⣬��ɵ��ø÷��������쳣�ر����ݿ�
	 * 
	 * @param conn
	 *            ���ݿ�����
	 */
	public void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
