package com.yccz.jdbcencapsulation.db;

import java.beans.SimpleBeanInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库连接
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
			// 加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库连接
	 * 
	 * @return 数据库连接，可能为 null
	 * @throws SQLException
	 *             数据库连接获取异常
	 */
	public Connection getConnection(String name) throws SQLException {
		return DriverManager.getConnection(url+name, user, password);
	}

	/**
	 * 调用者可自己关闭数据库，亦可调用该方法忽略异常关闭数据库
	 * 
	 * @param conn
	 *            数据库连接
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
