package com.yccz.jdbcencapsulation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yccz.jdbcencapsulation.bean.Commodity;
import com.yccz.jdbcencapsulation.bean.Mapping;

/**
 * ʵ������Ϣ
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            �������ͣ�������Ҫʹ��{@code Mapping}ע��������Ӧ���ݿ�ı������
 */
public class TypeToken<T extends Commodity> {

	private TypeHolder holder;

	public TypeToken(Class<T> clasz) {
		initFieldHolder(clasz);
	}

	// ��ʼ�����͵�ӳ���ϵ
	private void initFieldHolder(Class<T> clasz) {
		holder = new TypeHolder();
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
