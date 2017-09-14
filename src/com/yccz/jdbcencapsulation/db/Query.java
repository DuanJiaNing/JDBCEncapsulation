package com.yccz.jdbcencapsulation.db;

/**
 * 使用注解和反射实现对数据库的 CRUD 操作；
 * <p>
 * 1 通过注解将数据库中的表对应到具体的 java 实体类 <br>
 * 2 使用反射解析表信息和实体类间的映射关系
 * 
 * @author 2017/09/14 DuanJiaNing
 *
 */
public interface Query {

	/**
	 * 查询指定表中的所有数据
	 * @param clasz 表对应的数据实体类类型
	 * @return 查询结果
	 */
	<T> T[] query(Class<T> clasz);
}
