package com.yccz.jdbcencapsulation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ⸨���࣬��Ҫ���ڻ�����ݿ������{@link DataBase}}
 * 
 * @see DataBase
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBHelper {

	/**
	 * mysql ��ַ
	 */
	public static final String url = "jdbc:mysql://127.0.0.1/*?useUnicode=true&characterEncoding=utf-8&useSSL=false";

	/**
	 * ����
	 */
	public static final String driver = "com.mysql.jdbc.Driver";

	/**
	 * ���ݿ��û���
	 */
	public static final String user = "root";

	/**
	 * ���ݿ�����
	 */
	public static final String password = "123456";

	/**
	 * ���ݿ�����
	 */
	public static final String DATABASE_DEPOT = "depot";

	/**
	 * Ψһʵ��
	 */
	public volatile static DBHelper sINSTANCE;

	/**
	 * ���<code>DBHelper</code>ʵ��
	 * 
	 * @return ʵ��
	 */
	public static DBHelper getInstance() {
		if (sINSTANCE == null) {
			synchronized (DBHelper.class) {
				if (sINSTANCE == null) {
					sINSTANCE = new DBHelper();
				}
			}
		}

		return sINSTANCE;
	}

	/**
	 * ����һ��ʵ��
	 */
	private DBHelper() {
		try {
			// ��������
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������ݿ�����
	 * 
	 * @param name
	 *            ���ݿ�����
	 * @return ���ݿ����ӣ�����Ϊ null
	 */
	public DataBase getDataBase(String name) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url.replace("*", name), user, password);
			return new DataBase(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
