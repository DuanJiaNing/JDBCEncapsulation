package com.yccz.jdbcencapsulation;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 通用工具类
 * 
 * @author 2017/09/15 DuanJiaNing
 *
 */
public class Utils {

	/**
	 * 关闭一个 {@link Closeable}对象
	 */
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

	/**
	 * 关闭一个 {@link AutoCloseable}对象
	 */
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

	/**
	 * 输出包含时间信息的日志
	 */
	public static void log(CharSequence msg) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(f.format(new Date(System.currentTimeMillis())) + " " + msg);
	}

	/**
	 * 判断字符串是否是 null 的，没内容的，或内容为 'null' 的
	 * 
	 * @return 满足 null 或 无内容 或 内容为 'null' 返回 true，否则 false
	 */
	public static boolean isReal(CharSequence str) {
		return str != null && !str.equals("null") && str.length() > 0;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @return 数组为 null 或长度为 0 返回 true，否则 false
	 */
	public static <T> boolean isArrayEmpty(T... ts) {
		return ts == null || ts.length == 0;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @return 集合为 null 或大小为 0 返回 true，否则 false
	 */
	public static <T> boolean isListEmpty(List<T> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * 将指定年份，月份的日期转为从1790-1-1 00:00:00到当前时间总共经过的时间的毫秒数
	 */
	public static long getTimeInMillis(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTimeInMillis();
	}
}
