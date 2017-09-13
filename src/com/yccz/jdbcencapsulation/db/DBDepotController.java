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
 * 数据库 depot 工具类
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBDepotController<T extends Commodity> {

	private static final String DATABASE = "depot";

	private Connection conn = null;

	public DBDepotController() {
		init();
	}

	// 获得数据库连接
	private void init() {
		try {
			conn = DBHelper.getInstance().getConnection(DATABASE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public T[] select(Class<T> clasz) {
		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String sql = "select * from " + table;
			return select(clasz, sql);
		} else {
			return null;
		}
	}

	private String getTableName(Class<T> clasz) {
		TypeToken<T> token = new TypeToken<>(clasz);
		return token.getTabelName();
	}

	@SuppressWarnings("unchecked")
	public T[] select(Class<T> clasz, String sql) {

		PreparedStatement stat = null;
		ResultSet set = null;
		List<T> result = null;

		try {
			// 使结果集可滚动，方便获取结果集大小
			stat = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			set = stat.executeQuery();

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

	public T[] select(Class<T> clasz, String[] whereCase, String[] whereValues) {
		String table = getTableName(clasz);
		if (Utils.isReal(table)) {
			String where = constructConditions(whereCase, whereValues);
			String sql = "select * from " + table + " " + where;
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

	private String constructConditions(String[] whereCase, String[] whereValues) {
		if (whereCase == null || whereCase.length == 0 || whereValues == null || whereValues.length == 0) {
			return "";
		}

		return null;
	}

}
