package com.yccz.jdbcencapsulation.db;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(f.format(new Date(System.currentTimeMillis()))+" "+msg);
	}
	
	public static boolean isReal(CharSequence str) {
		return str != null && !str.equals("null") && str.length() > 0;
	}
	
}
