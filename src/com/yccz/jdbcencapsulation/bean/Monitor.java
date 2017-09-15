package com.yccz.jdbcencapsulation.bean;

import java.io.Serializable;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * ��ʾ��������Ϣ����ʵ���� ��Ӧ���ݿ��: monitor
 * 
 * @author 2017/09/13 DuanJiaNing
 */
@Mapping("monitor")
public class Monitor extends Data implements Serializable {
	/**
	 * ��ʾ������
	 */
	@Mapping("name")
	private String name;

	/**
	 * ��ʾ���۸�
	 */
	@Mapping("price")
	private float price;

	/**
	 * ��ʾ������
	 */
	@Mapping("description")
	private String description;

	/**
	 * ������
	 */
	@Mapping("monthly_sales")
	private int monthlySales;

	/**
	 * ָ���������ֵ����ͨ�����ڡ����¡����� id ������Ψһȷ��Ŀ��������ݣ���������Ϊ�µ�ֵ
	 */
	public Monitor(int id, String name, float price, String description, int monthlySales) {
		super(id);
		this.name = name;
		this.price = price;
		this.description = description;
		this.monthlySales = monthlySales;
	}

	/**
	 * ����ָ�� id �Ĺ��캯������Ϊ id �����������Զ����� ��ͨ�����ڡ�����������
	 */
	public Monitor(String name, float price, String description, int monthlySales) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.monthlySales = monthlySales;
	}

	// ���乹��������Ҫ��һ��Ĭ�ϵĿɷ��ʹ��캯��
	public Monitor() {
	}

	@Override
	public String toString() {
		return "Monitor [name=" + name + ", price=" + price + ", description=" + description + ", monthlySales="
				+ monthlySales + ", id=" + super.toString() + "]";
	}

}
