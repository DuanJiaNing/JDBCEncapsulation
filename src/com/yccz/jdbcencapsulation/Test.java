package com.yccz.jdbcencapsulation;

import com.yccz.jdbcencapsulation.bean.Monitor;
import com.yccz.jdbcencapsulation.db.DBDepotController;
import com.yccz.jdbcencapsulation.db.Utils;

public class Test {
	public static void main(String[] args) {

		DBDepotController<Monitor> controller = new DBDepotController<>();
		Monitor[] ms = controller.select(Monitor.class);
		if (ms != null && ms.length > 0) {
			for (Monitor m : ms) {
				Utils.log(m.toString());
			}
		} else {
			Utils.log("empty");
		}

	}

}
