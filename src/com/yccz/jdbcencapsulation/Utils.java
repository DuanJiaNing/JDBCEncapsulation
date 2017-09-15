package com.yccz.jdbcencapsulation;

import java.io.Closeable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ͨ�ù�����
 * 
 * @author 2017/09/15 DuanJiaNing
 *
 */
public class Utils {

	/**
	 * �ر�һ�� {@link Closeable}����
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
	 * �ر�һ�� {@link AutoCloseable}����
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
	 * �������ʱ����Ϣ����־
	 */
	public static void log(CharSequence msg) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(f.format(new Date(System.currentTimeMillis())) + " " + msg);
	}

	/**
	 * �ж��ַ����Ƿ��� null �ģ�û���ݵģ�������Ϊ 'null' ��
	 * 
	 * @return ���� null �� ������ �� ����Ϊ 'null' ���� true������ false
	 */
	public static boolean isReal(CharSequence str) {
		return str != null && !str.equals("null") && str.length() > 0;
	}

	/**
	 * �ж������Ƿ�Ϊ��
	 * 
	 * @return ����Ϊ null �򳤶�Ϊ 0 ���� true������ false
	 */
	public static <T> boolean isArrayEmpty(T... ts) {
		return ts == null || ts.length == 0;
	}

	/**
	 * �жϼ����Ƿ�Ϊ��
	 * 
	 * @return ����Ϊ null ���СΪ 0 ���� true������ false
	 */
	public static <T> boolean isListEmpty(List<T> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * ��ָ����ݣ��·ݵ�����תΪ��1790-1-1 00:00:00����ǰʱ���ܹ�������ʱ��ĺ�����
	 */
	public static long getTimeInMillis(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTimeInMillis();
	}
}
