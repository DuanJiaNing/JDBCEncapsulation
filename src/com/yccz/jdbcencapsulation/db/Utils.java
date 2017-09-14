package com.yccz.jdbcencapsulation.db;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

	public static void close(Closeable obj) {
		if (obj == null) {
			return;
		}

		try {
			obj.close();
		} catch (IOException e) {
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
			e.printStackTrace();
		}
	}

	public static void log(CharSequence msg) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(f.format(new Date(System.currentTimeMillis())) + " " + msg);
	}

	public static boolean isReal(CharSequence str) {
		return str != null && !str.equals("null") && str.length() > 0;
	}

	public static <T> boolean isArrayEmpty(T... ts) {
		return ts == null || ts.length == 0;
	}

	public static <T> boolean isListEmpty(List<T> list) {
		return list == null || list.size() == 0;
	}

	public static long getTimeInMillis(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTimeInMillis();
	}
}
