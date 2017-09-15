package com.yccz.jdbcencapsulation.bean;

import java.io.Serializable;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * 显示器基本信息数据实体类 对应数据库表: monitor
 * 
 * @author 2017/09/13 DuanJiaNing
 */
@Mapping("monitor")
public class Monitor extends Data implements Serializable {
	/**
	 * 显示器名称
	 */
	@Mapping("name")
	private String name;

	/**
	 * 显示器价格
	 */
	@Mapping("price")
	private float price;

	/**
	 * 显示器描述
	 */
	@Mapping("description")
	private String description;

	/**
	 * 月销量
	 */
	@Mapping("monthly_sales")
	private int monthlySales;

	/**
	 * 指定所有域的值，这通常用于【更新】操作 id 域被用于唯一确定目标更新数据，其他域则为新的值
	 */
	public Monitor(int id, String name, float price, String description, int monthlySales) {
		super(id);
		this.name = name;
		this.price = price;
		this.description = description;
		this.monthlySales = monthlySales;
	}

	/**
	 * 无需指定 id 的构造函数，因为 id 是主键，会自动生成 这通常用于【新增】操作
	 */
	public Monitor(String name, float price, String description, int monthlySales) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.monthlySales = monthlySales;
	}

	// 反射构建对象需要有一个默认的可访问构造函数
	public Monitor() {
	}

	@Override
	public String toString() {
		return "Monitor [name=" + name + ", price=" + price + ", description=" + description + ", monthlySales="
				+ monthlySales + ", id=" + super.toString() + "]";
	}

}
