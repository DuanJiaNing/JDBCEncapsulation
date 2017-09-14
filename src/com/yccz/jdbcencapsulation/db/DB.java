package com.yccz.jdbcencapsulation.db;

/**
 * ʹ��ע��ͷ���ʵ�ֶ����ݿ�� CRUD ������
 * <p>
 * 1 ͨ��ע�⽫���ݿ��еı��Ӧ������� java ʵ���� <br>
 * 2 ʹ�÷����������Ϣ��ʵ������ӳ���ϵ
 * 
 * @author 2017/09/14 DuanJiaNing
 *
 */
public interface DB {

	/**
	 * 
	 * @param clasz
	 * @return
	 */
	<T> T[] read(Class<T> clasz);

	/**
	 * 
	 * @param ts
	 */
	<T> void create(T... ts);

	/**
	 * 
	 * @param ts
	 */
	<T> void update(T... ts);

	/**
	 * 
	 * @param ts
	 */
	<T> void delete(T... ts);
}
