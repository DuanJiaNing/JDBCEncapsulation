package com.yccz.jdbcencapsulation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yccz.jdbcencapsulation.bean.Commodity;
import com.yccz.jdbcencapsulation.bean.Mapping;

/**
 * 实体类信息
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            货物类型，该类需要使用{@code Mapping}注解标明其对应数据库的表的名字
 */
public class TypeToken<T extends Commodity> {

	private TypeHolder holder;

	public TypeToken(Class<T> clasz) {
		initFieldHolder(clasz);
	}

	// 初始化类型的映射关系
	private void initFieldHolder(Class<T> clasz) {
		holder = new TypeHolder();
		Mapping map = clasz.getAnnotation(Mapping.class);
		if (map != null) {
			holder.name = map.value();
		}
	}

	/**
	 * 获得类对应表的表名
	 * 
	 * @return 表名
	 */
	public String getTabelName() {
		return holder.name;
	}

	/**
	 * 保存数据库表与数据实体的映射关系
	 * 
	 * @author 2017/09/13 DuanJiaNing
	 *
	 */
	public static class TypeHolder {

		/**
		 * 数据库表名
		 */
		public String name;
	}

}
