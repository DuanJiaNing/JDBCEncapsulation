package com.yccz.jdbcencapsulation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yccz.jdbcencapsulation.bean.Commodity;
import com.yccz.jdbcencapsulation.bean.Mapping;

/**
 * ʵ�����ֶ���Ϣ
 * 
 * @author 2017/09/13 DuanJiaNing
 * @param T
 *            �������ͣ����������Ҫʹ��{@code Mapping}ע��������Ӧ���ݿ���ֶ���
 */
public class FieldToken<T extends Commodity> {

	private final List<FieldHolder> holders;

	public FieldToken(Class<T> clasz) {
		holders = new ArrayList<FieldHolder>();
		initFieldHolder(clasz);
	}

	// ��ʼ�����͵�ӳ���ϵ
	private void initFieldHolder(Class<T> clasz) {
		Field[] fs = clasz.getDeclaredFields();
		for (Field f : fs) {
			Mapping mag = f.getAnnotation(Mapping.class);
			if (mag != null) {
				FieldHolder fh = new FieldHolder();
				fh.name = mag.value();
				fh.field = f;
				holders.add(fh);
			}
		}

		@SuppressWarnings("unchecked")
		Class<T> supez = (Class<T>) clasz.getSuperclass();
		if (supez != null) {
			initFieldHolder(supez);
		}
	}

	/**
	 * ���ӳ���б�
	 * 
	 * @return ӳ���б�
	 */
	public List<FieldHolder> getFieldHolder() {
		return holders;
	}

	/**
	 * �������ݿ��ֶ���������ʵ���ӳ���ϵ
	 * 
	 * @author 2017/09/13 DuanJiaNing
	 *
	 */
	public static class FieldHolder {
		/**
		 * ���ݿ���ȡ�������ݽ�Ҫ�����ʵ�����ֶ�
		 */
		public Field field;

		/**
		 * ���ݿ��ֶ���
		 */
		public String name;
	}

}
