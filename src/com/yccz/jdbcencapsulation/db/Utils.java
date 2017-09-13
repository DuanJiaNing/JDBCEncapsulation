package com.yccz.jdbcencapsulation.db;

import java.io.Closeable;
import java.io.IOException;

public class Utils {

	public static void close(Closeable obj) {
		if (obj == null) {
			return;
		}
		
		try {
			obj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeAutoCloseable(AutoCloseable obj) {
		if (obj == null) {
			return;
		}
		
		try {
			obj.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void log(CharSequence msg) {
		System.out.println(msg);
	}
	
	public static boolean isReal(CharSequence str) {
		return str != null && !str.equals("null") && str.length() > 0;
	}
	
}
