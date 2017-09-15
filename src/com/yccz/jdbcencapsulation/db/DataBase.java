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
import com.yccz.jdbcencapsulation.Utils;
import com.yccz.jdbcencapsulation.bean.Data;

/**
 * 数据库 CRUD 操作实现类
 * <p>
 * 在不再使用时要{@code #close()}方法关闭数据库连接
 * <p>
 * 使用{@link DBHelper#getDataBase(String)}方法指定要操作的数据库，亦可指定数据库连接直接调用构造方法获得实例
 * 
 * @see DBHelper
 * @see DB
 * @see Data
 * 
 * @author 2017/09/13 DuanJiaNing
 */
public class DataBase implements DB {

	/**
	 * 数据库连接
	 */
	private final Connection conn;

	/**
	 * 构造一个<code>DataBase</code>实例，应通过{@link DBHelper#getDataBase(String)}方法创建
	 * 
	 * @param connection
	 *            数据库连接
	 */
	DataBase(Connection connection) {
		this.conn = connection;
	}

	/**
	 * 根据实体类类型信息获得其对应的数据表名称
	 */
	private <T extends Data> String getTableName(Class<T> clasz) {
		Token<String> token = new TableToken<>(clasz);
		return token.get();
	}

	/**
	 * 获取结果集大小，调用前提为 prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY)
	 */
	private int getResultSetSize(ResultSet set) throws SQLException {
		int size = -1;
		if (set != null) {
			set.last();
			size = set.getRow();
			set.beforeFirst();
		}
		return size;
	}

	/**
	 * 构造 sql 语句 where 条件
	 */
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
	 * 关闭数据库
	 * 
	 * @param conn
	 *            数据库连接对象
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
	public <T extends Data> T[] query(Class<T> clasz, String sql) {
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
			Token<List<FieldToken.FieldHolder>> token = new FieldToken<>(clasz);
			List<FieldToken.FieldHolder> fh = token.get();
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
	public <T extends Data> T[] query(Class<T> clasz, String[] whereCase, String[] whereValues) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Data> T[] query(Class<T> clasz) {
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Data> boolean insert(T... ts) {
		if (Utils.isArrayEmpty(ts)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Class<T> clasz = (Class<T>) ts[0].getClass();
		String table = getTableName(clasz);
		if (!Utils.isReal(table)) {
			return false;
		}

		// 获取映射关系
		Token<List<FieldToken.FieldHolder>> token = new FieldToken<>(clasz);
		List<FieldToken.FieldHolder> fh = token.get();
		if (Utils.isListEmpty(fh)) {
			return false;
		}

		StringBuilder caseBuilder = new StringBuilder();
		StringBuilder valueBuilder = new StringBuilder();

		PreparedStatement stat = null;
		try {

			for (T t : ts) {
				caseBuilder.delete(0, caseBuilder.length());
				valueBuilder.delete(0, valueBuilder.length());
				int size = fh.size();
				for (int i = 0; i < size; i++) {
					FieldToken.FieldHolder f = fh.get(i);
					String v;
					String tn = f.name;
					Field field = f.field;
					if (tn.equals("id")) {
						v = "DEFAULT";
					} else {
						try {

							// 利用反射获得值
							field.setAccessible(true);
							v = field.get(t).toString();

						} catch (IllegalArgumentException e) {
							e.printStackTrace();
							return false;
						} catch (IllegalAccessException e) {
							e.printStackTrace();
							return false;
						}

					}

					caseBuilder.append(tn);
					if (field.getType().getName().equals(String.class.getName())) {
						valueBuilder.append('\'').append(v).append('\'');
					} else {
						valueBuilder.append(v);
					}

					if (i < size - 1) {
						caseBuilder.append(',');
						valueBuilder.append(',');
					}
				}

				String sql = "insert into " + table + "(" + caseBuilder.toString() + ") values("
						+ valueBuilder.toString() + ")";
				Utils.log(sql);

				stat = conn.prepareStatement(sql);
				stat.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utils.closeAutoCloseable(stat);
		}

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Data> boolean update(T... ts) {
		if (Utils.isArrayEmpty(ts)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Class<T> clasz = (Class<T>) ts[0].getClass();
		String table = getTableName(clasz);
		if (!Utils.isReal(table)) {
			return false;
		}

		// 获取映射关系
		Token<List<FieldToken.FieldHolder>> token = new FieldToken<>(clasz);
		List<FieldToken.FieldHolder> fh = token.get();
		if (Utils.isListEmpty(fh)) {
			return false;
		}

		PreparedStatement stat = null;
		try {

			for (T t : ts) {
				StringBuilder valueBuilder = new StringBuilder();
				int size = fh.size();
				String id = "";
				for (int i = 0; i < size; i++) {
					FieldToken.FieldHolder f = fh.get(i);
					String v = "";
					String tn = f.name;
					Field field = f.field;

					try {

						// 利用反射获得值
						field.setAccessible(true);
						v = field.get(t).toString();

					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return false;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return false;
					}

					if (!tn.equals("id")) {
						valueBuilder.append(tn).append('=');
						if (field.getType().getName().equals(String.class.getName())) {
							valueBuilder.append('\'').append(v).append('\'');
						} else {
							valueBuilder.append(v);
						}

						if (i != size - 2) {
							valueBuilder.append(',');
						}
					} else {
						id = v;
					}

				}

				String where = constructConditions(new String[] { "id" }, new String[] { id });
				String sql = "update " + table + " set " + valueBuilder.toString() + " where" + where;
				Utils.log(sql);

				stat = conn.prepareStatement(sql);
				stat.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utils.closeAutoCloseable(stat);
		}

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Data> boolean delete(Class<T> clasz, int... ids) {
		if (Utils.isArrayEmpty(ids)) {
			return false;
		}

		String table = getTableName(clasz);
		if (!Utils.isReal(table)) {
			return false;
		}

		PreparedStatement stat = null;
		try {

			for (int id : ids) {

				String where = constructConditions(new String[] { "id" }, new String[] { id + "" });
				String sql = "delete from " + table + " where" + where;
				Utils.log(sql);

				stat = conn.prepareStatement(sql);
				stat.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utils.closeAutoCloseable(stat);
		}

	}

}
