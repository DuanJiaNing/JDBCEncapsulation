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
public interface Query {

	/**
	 * ��ѯָ�����е���������
	 * @param clasz ���Ӧ������ʵ��������
	 * @return ��ѯ���
	 */
	<T> T[] query(Class<T> clasz);
}
