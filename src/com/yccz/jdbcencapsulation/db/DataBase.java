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
import com.yccz.jdbcencapsulation.TypeToken;
import com.yccz.jdbcencapsulation.bean.Commodity;

/**
 * 数据库，在不再使用时要{@code #close()}方法关闭数据库连接
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DataBase {

	// 数据库连接
	private final Connection conn;

	public DataBase(Connection connection) {
		this.conn = connection;
	}

	/**
	 * 查询表中的所有数据
	 * 
	 * @param clasz
	 *            要查询的表对应的实体数据类类型
	 * @return 查询结果
	 */
	public <T extends Commodity> T[] select(Class<T> clasz) {
		if (clasz == null) {
			return null;
		}
		
		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String sql = "select * from " + table;
			return select(clasz, sql);
		} else {
			return null;
		}
	}

	// 根据实体类类型信息获得其对应的数据表名称
	private <T extends Commodity> String getTableName(Class<T> clasz) {
		TypeToken<T> token = new TypeToken<>(clasz);
		return token.getTabelName();
	}

	/**
	 * 根据 sql 语句查询表中数据
	 * 
	 * @param clasz
	 *            要查询的表对应的实体数据类类型
	 * @param sql
	 *            sql 语句
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public <T extends Commodity> T[] select(Class<T> clasz, String sql) {
		if (clasz == null || !Utils.isReal(sql)) {
			return null;
		}
		
		PreparedStatement stat = null;
		ResultSet set = null;
		List<T> result = null;

		try {
			// 使结果集可滚动，方便获取结果集大小
			stat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			set = stat.executeQuery();

			Utils.log(sql);

			// 获取大小
			int size = getResultSetSize(set);
			if (size <= 0) {
				return null;
			}

			// 获取映射关系
			FieldToken<T> token = new FieldToken<>(clasz);
			List<FieldToken.FieldHolder> fh = token.getFieldHolder();
			result = new ArrayList<>();

			// 利用反射将查询结果赋给目标类型
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
	 * 根据条件查询表中数据
	 * 
	 * @param clasz
	 *            要查询的表对应的实体数据类类型
	 * @param whereCase
	 *            sql 语句 where 对应列名
	 * @param whereValues
	 *            whereCase 对应的值
	 * @return 查询结果
	 */
	public <T extends Commodity> T[] select(Class<T> clasz, String[] whereCase, String[] whereValues) {
		if (clasz == null) {
			return null;
		}
		
		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String where = constructConditions(whereCase, whereValues);
			String sql = "select * from " + table + (where == null ? "" : " where" + where);
			return select(clasz, sql);
		} else {
			return null;
		}
	}

	// 获取结果集大小，调用前提为 prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
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

	// 构造 sql 语句 where 条件
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
	 * 调用者可自己关闭数据库，亦可调用该方法忽略异常关闭数据库
	 * 
	 * @param conn
	 *            数据库连接
	 */
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
