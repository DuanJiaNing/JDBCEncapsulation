package com.yccz.jdbcencapsulation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库辅助类，主要用于获得数据库操作类{@link DataBase}}
 * 
 * @see DataBase
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBHelper {

	/**
	 * mysql 地址
	 */
	private final String url = "jdbc:mysql://127.0.0.1/*?useUnicode=true&characterEncoding=utf-8&useSSL=false";

	/**
	 * 驱动
	 */
	private final String driver = "com.mysql.jdbc.Driver";

	/**
	 * 数据库用户名
	 */
	private final String user = "root";

	/**
	 * 数据库密码
	 */
	private final String password = "123456";

	/**
	 * 数据库名字
	 */
	public static final String DATABASE_DEPOT = "depot";

	/**
	 * 唯一实例
	 */
	public volatile static DBHelper sINSTANCE;

	/**
	 * 获得<code>DBHelper</code>实例
	 * 
	 * @return 实例
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
	 * 构造一个实例
	 */
	private DBHelper() {
		try {
			// 加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库连接
	 * 
	 * @param name
	 *            数据库名字
	 * @return 数据库连接，可能为 null
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
