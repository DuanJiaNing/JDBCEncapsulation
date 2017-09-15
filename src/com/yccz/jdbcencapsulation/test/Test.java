package com.yccz.jdbcencapsulation.test;

import java.util.function.Consumer;

import com.yccz.jdbcencapsulation.Utils;
import com.yccz.jdbcencapsulation.bean.Data;
import com.yccz.jdbcencapsulation.bean.Monitor;
import com.yccz.jdbcencapsulation.bean.MonitorDetail;
import com.yccz.jdbcencapsulation.db.DBHelper;
import com.yccz.jdbcencapsulation.db.DataBase;

/**
 * ������
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
public class Test {

	private final DataBase dataBase;

	// ��������
	private Monitor mo1 = new Monitor(1, "Philips/������ 278E8Q", 1329.00f, "������ 27Ӣ��������ʾ�� 278E8Q��������32��ʾ��", 3034);
	private Monitor mo2 = new Monitor(2, "���� SW270A", 799.09f, "�����¿�27Ӣ��IPS��Һ��������ʾ����Ϸ�羺���", 3489);
	private Monitor mo3 = new Monitor(3, "Dell/���� SE2416Hm", 899.09f, "��������Ӫ��Dell/���� SE2416HM 23.8Ӣ��LED����", 1013);

	private MonitorDetail md1 = new MonitorDetail(1, Utils.getTimeInMillis(2017, 6), "������ ", "278E8Q", 32);
	private MonitorDetail md2 = new MonitorDetail(2, Utils.getTimeInMillis(2015, 2), "����", "SW270A", 27);
	private MonitorDetail md3 = new MonitorDetail(3, Utils.getTimeInMillis(2017, 9), "����", "SE2416Hm", 23.8f);

	public Test() {
		dataBase = DBHelper.getInstance().getDataBase(DBHelper.DATABASE_DEPOT);
	}

	public static void main(String[] args) {

		Test test = new Test();
		if (test.dataBase == null) {
			return;
		}

		// test.testForInsert();
		// test.testForUpdate();
		// test.testForDelete();

		test.print(Monitor.class);
		test.print(MonitorDetail.class);

		test.dataBase.close();
		
	}

	private void testForDelete() {
		dataBase.delete(Monitor.class, 3);
		dataBase.delete(MonitorDetail.class, 1, 2);
	}

	private void testForUpdate() {
		Monitor mo = new Monitor(1, "Philips/������ 278E8Q", 1329.00f, "������ 27Ӣ��������ʾ�� 278E8Q��������32��ʾ��", 5555);
		MonitorDetail md = new MonitorDetail(2, Utils.getTimeInMillis(2015, 2), "����", "SW270A", 33.8f);

		dataBase.update(md);
		dataBase.update(mo);
	}

	private void testForInsert() {
		dataBase.insert(mo1, mo2, mo3);
		dataBase.insert(md1, md2, md3);
	}

	@SuppressWarnings("unused")
	private <T extends Data> void print(Class<T> clasz) {
		System.out.println();
		P.accept(clasz.getName());

		T[] ms = dataBase.query(clasz);
		// Monitor[] ms = dataBase.query(Monitor.class, new String[] { "price", "id" },
		// new String[] { "18.28", "1" });
		// Monitor[] ms = dataBase.query(Monitor.class, "select * from monitor");

		if (ms != null && ms.length > 0) {
			forEach(ms);
		} else {
			Utils.log("empty");
		}
	}

	private static Consumer<CharSequence> P = msg -> Utils.log(msg);

	private static <T> void forEach(T... ts) {
		for (T t : ts) {
			P.accept(t.toString());
		}
	}
}
