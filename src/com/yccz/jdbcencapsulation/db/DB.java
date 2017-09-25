package com.yccz.jdbcencapsulation.db;

import com.yccz.jdbcencapsulation.bean.Data;

/**
 * 使用注解和反射实现对数据库的 CRUD 操作；
 * <p>
 * 1 通过注解将数据库中的表对应到具体的 java 实体类 <br>
 * 2 使用反射解析表信息和实体类间的映射关系
 * 
 * @author 2017/09/14 DuanJiaNing
 *
 */
public interface DB {

	/**
	 * 查询指定表中的所有数据
	 * @param clasz 表对应的数据实体类类型
	 * @return 查询结果
	 */
	<T extends Data> T[] query(Class<T> clasz);
	
	/**
	 * 插入数据到指定表中，
	 * @param ts 要插入的数据
	 * @return 插入成功返回 true，否则 false
	 */
	<T extends Data> boolean insert(T...ts);
	
	/**
	 * 更新特定表中的数据
	 * @param ts 更新的数据
	 * @return 更新成功返回 true，否则 false
	 */
	<T extends Data> boolean update(T...ts);
	
	/**
	 * 删除特定表中的数据
	 * @param clasz 
	 * @param ids 数据的 id 值
	 * @return 成功返回 true，否则 false
	 */
	<T extends Data> boolean delete(Class<T> clasz,int...ids);
}
