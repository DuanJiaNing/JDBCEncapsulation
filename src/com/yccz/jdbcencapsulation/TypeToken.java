package com.yccz.jdbcencapsulation;

import com.yccz.jdbcencapsulation.bean.Mapping;

/**
 * ʵ������Ϣ
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            �������ͣ�������Ҫʹ��{@code Mapping}ע��������Ӧ���ݿ�ı������
 */
public class TypeToken<T> {

	private final TypeHolder holder;

	public TypeToken(Class<T> clasz) {
		this.holder = new TypeHolder();
		
		if (clasz != null) {
			initFieldHolder(clasz);
		}
	}

	// ��ʼ�����͵�ӳ���ϵ
	private void initFieldHolder(Class<T> clasz) {
		Mapping map = clasz.getAnnotation(Mapping.class);
		if (map != null) {
			holder.name = map.value();
		}
	}

	/**
	 * ������Ӧ��ı���
	 * 
	 * @return ����
	 */
	public String getTabelName() {
		return holder.name;
	}

	/**
	 * �������ݿ��������ʵ���ӳ���ϵ
	 * 
	 * @author 2017/09/13 DuanJiaNing
	 *
	 */
	public static class TypeHolder {

		/**
		 * ���ݿ����
		 */
		public String name;
	}

}
