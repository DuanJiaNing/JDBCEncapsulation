package com.yccz.jdbcencapsulation.db;

import java.beans.SimpleBeanInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ���ݿ�����
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBHelper {

	public static final String url = "jdbc:mysql://127.0.0.1/";
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	public volatile static DBHelper sINSTANCE;

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

	private DBHelper() {
		try {
			// ��������
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ������ݿ�����
	 * 
	 * @return ���ݿ����ӣ�����Ϊ null
	 * @throws SQLException
	 *             ���ݿ����ӻ�ȡ�쳣
	 */
	public Connection getConnection(String name) throws SQLException {
		return DriverManager.getConnection(url+name, user, password);
	}

	/**
	 * �����߿��Լ��ر����ݿ⣬��ɵ��ø÷��������쳣�ر����ݿ�
	 * 
	 * @param conn
	 *            ���ݿ�����
	 */
	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
