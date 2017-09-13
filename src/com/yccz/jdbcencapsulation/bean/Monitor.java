package com.yccz.jdbcencapsulation.bean;

/**
 * ��ʾ��������Ϣ����ʵ����
 * ��Ӧ���ݿ��: monitor
 * @author 2017/09/13 DuanJiaNing
 */
@Mapping("monitor")
public class Monitor extends Commodity {

	/**
	 * ��ʾ������
	 */
	@Mapping("name")
	String name;
	
	/**
	 * ��ʾ���۸�
	 */
	@Mapping("price")
	float price;
	
	/**
	 * ��ʾ������
	 */
	@Mapping("description")
	String description;
	
	/**
	 * ������
	 */
	@Mapping("monthly_sales")
	int monthlySales;

	@Override
	public String toString() {
		return "Monitor [name=" + name + ", price=" + price + ", description=" + description + ", monthlySales="
				+ monthlySales + ", id=" + id + "]";
	}
	
	
	
}
