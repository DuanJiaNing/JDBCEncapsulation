package com.yccz.jdbcencapsulation;

import com.yccz.jdbcencapsulation.bean.Data;

/**
 * 实体类类型信息
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            货物类型，该类需要使用{@code Mapping}注解标明其对应数据库的表的名字
 */
public class TableToken<T extends Data> implements Token<String> {

	/**
	 * 表名
	 */
	private String tableName;

	public TableToken(Class<T> clasz) {
		if (clasz != null) {
			getTableName(clasz);
		}
	}

	// 利用反射获得注解标明的表名
	private void getTableName(Class<T> clasz) {
		Mapping map = clasz.getAnnotation(Mapping.class);
		if (map != null) {
			tableName = map.value();
		}
	}

	/**
	 * 获得类对应表的表名
	 */
	@Override
	public String get() {
		return tableName;
	}

}
