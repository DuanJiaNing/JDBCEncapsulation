package com.yccz.jdbcencapsulation;

import com.yccz.jdbcencapsulation.bean.Data;

/**
 * ʵ����������Ϣ
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            �������ͣ�������Ҫʹ��{@code Mapping}ע��������Ӧ���ݿ�ı������
 */
public class TableToken<T extends Data> implements Token<String> {

	/**
	 * ����
	 */
	private String tableName;

	public TableToken(Class<T> clasz) {
		if (clasz != null) {
			getTableName(clasz);
		}
	}

	// ���÷�����ע������ı���
	private void getTableName(Class<T> clasz) {
		Mapping map = clasz.getAnnotation(Mapping.class);
		if (map != null) {
			tableName = map.value();
		}
	}

	/**
	 * ������Ӧ��ı���
	 */
	@Override
	public String get() {
		return tableName;
	}

}
