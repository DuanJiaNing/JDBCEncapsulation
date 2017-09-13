package com.yccz.jdbcencapsulation;

import java.util.Calendar;

import com.yccz.jdbcencapsulation.bean.Monitor;
import com.yccz.jdbcencapsulation.bean.MonitorDetail;
import com.yccz.jdbcencapsulation.db.Utils;

public class Test {
	public static void main(String[] args) {

		FieldToken<Monitor> ft = new FieldToken<>(Monitor.class);
		for (FieldToken.FieldHolder h : ft.getFieldHolder()) {
			Utils.log(h.field.getName()+" an:"+h.name);
		}
	}
	
}
