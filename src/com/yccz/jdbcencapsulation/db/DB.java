package com.yccz.jdbcencapsulation.db;

import com.yccz.jdbcencapsulation.bean.Data;

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
	 * ��ѯָ�����е���������
	 * @param clasz ���Ӧ������ʵ��������
	 * @return ��ѯ���
	 */
	<T extends Data> T[] query(Class<T> clasz);
	
	/**
	 * �������ݵ�ָ�����У�
	 * @param ts Ҫ���������
	 * @return ����ɹ����� true������ false
	 */
	<T extends Data> boolean insert(T...ts);
	
	/**
	 * �����ض����е�����
	 * @param ts ���µ�����
	 * @return ���³ɹ����� true������ false
	 */
	<T extends Data> boolean update(T...ts);
	
	/**
	 * ɾ���ض����е�����
	 * @param clasz 
	 * @param ids ���ݵ� id ֵ
	 * @return �ɹ����� true������ false
	 */
	<T extends Data> boolean delete(Class<T> clasz,int...ids);
}
