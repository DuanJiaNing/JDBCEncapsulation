package com.yccz.jdbcencapsulation;

/**
 * ���ʵ����ӳ�䵽���ݿ������
 * 
 * @author 2017/09/14 DuanJiaNing
 *
 * @param <E> Ŀ������
 */
public interface Token<E> {

	/**
	 * �������
	 * @return ����ֵ
	 */
	E get();
	
}
