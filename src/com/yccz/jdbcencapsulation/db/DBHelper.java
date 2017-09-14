package com.yccz.jdbcencapsulation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�����
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class DBHelper {

	public static final String url = "jdbc:mysql://127.0.0.1/*?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";

	public static final String DATABASE_DEPOT = "depot";

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
			e.printStackTrace();
		}
	}

	/**
	 * ������ݿ�����
	 * 
	 * @param name ���ݿ�����
	 * @return ���ݿ����ӣ�����Ϊ null
	 */
	public DataBase getDataBase(String name){
		Connection conn;
		try {
			conn = DriverManager.getConnection(url.replace("*", name), user, password);
			return new DataBase(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
