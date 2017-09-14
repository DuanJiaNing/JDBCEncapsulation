package com.yccz.jdbcencapsulation.bean;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * 数据库数据对应实体类的基类
 * 
 * @author 2017/09/14 DuanJiaNing
 *
 */
public class Data {

	@Mapping("id")
	private int id;

	public Data(int id) {
		this.id = id;
	}

	public Data() {
	}

	@Override
	public String toString() {
		return "Data [id=" + id + "]";
	}


}
