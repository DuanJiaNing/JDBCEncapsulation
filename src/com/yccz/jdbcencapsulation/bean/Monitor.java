package com.yccz.jdbcencapsulation.bean;

/**
 * 显示器基本信息数据实体类
 * 对应数据库表: monitor
 * @author 2017/09/13 DuanJiaNing
 */
@Mapping("monitor")
public class Monitor extends Commodity {

	/**
	 * 显示器名称
	 */
	@Mapping("name")
	String name;
	
	/**
	 * 显示器价格
	 */
	@Mapping("price")
	float price;
	
	/**
	 * 显示器描述
	 */
	@Mapping("description")
	String description;
	
	/**
	 * 月销量
	 */
	@Mapping("monthly_sales")
	int monthlySales;

	@Override
	public String toString() {
		return "Monitor [name=" + name + ", price=" + price + ", description=" + description + ", monthlySales="
				+ monthlySales + ", id=" + id + "]";
	}
	
	
	
}
