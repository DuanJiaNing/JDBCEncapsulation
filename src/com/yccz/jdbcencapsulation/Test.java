package com.yccz.jdbcencapsulation;

import java.util.function.Consumer;

import com.yccz.jdbcencapsulation.bean.MonitorDetail;
import com.yccz.jdbcencapsulation.db.DBHelper;
import com.yccz.jdbcencapsulation.db.DataBase;
import com.yccz.jdbcencapsulation.db.Utils;

public class Test {
	public static void main(String[] args) {

		DataBase dataBase = DBHelper.getInstance().getDataBase(DBHelper.DATABASE_DEPOT);
		if (dataBase != null) {
			
//			Monitor[] ms = dataBase.read(Monitor.class,new String[] {"price","id"},new String[] {"18.28","1"});
//			Monitor[] ms = dataBase.read(Monitor.class);
//			Monitor[] ms = dataBase.read(Monitor.class,"select * from monitor");
			
			MonitorDetail[] ms = dataBase.read(MonitorDetail.class);
					
			if (ms != null && ms.length > 0) {
				forEach(ms);
			
			} else {
				Utils.log("empty");
			}
			
		}
	}
			
	private static Consumer<CharSequence> P = msg -> Utils.log(msg);

	private static <T> void forEach(T... ts) {
		for (T t : ts) {
			P.accept(t.toString());
		}
	}

}
