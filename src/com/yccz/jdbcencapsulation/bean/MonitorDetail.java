package com.yccz.jdbcencapsulation.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * 显示器详细参数数据实体类 对应数据库表: monitor_detail
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
@Mapping("monitor_detail")
public class MonitorDetail extends Data implements Serializable{

	/**
	 * 上市时间
	 */
	@Mapping("listing_time")
	private long listingTime;

	/**
	 * 品牌
	 */
	@Mapping("brand")
	private String brand;

	/**
	 * 型号
	 */
	@Mapping("model")
	private String model;

	/**
	 * 屏幕尺寸
	 */
	@Mapping("size")
	private float size;

	/**
	 * 指定所有域的值，这通常用于【更新】操作 id 域被用于唯一确定目标更新数据，其他域则为新的值
	 */
	public MonitorDetail(int id, long listingTime, String brand, String model, float size) {
		super(id);
		this.listingTime = listingTime;
		this.brand = brand;
		this.model = model;
		this.size = size;
	}

	/**
	 * 无需指定 id 的构造函数，因为 id 是主键，会自动生成 这通常用于【新增】操作
	 */
	public MonitorDetail(long listingTime, String brand, String model, float size) {
		this.listingTime = listingTime;
		this.brand = brand;
		this.model = model;
		this.size = size;
	}

	// 反射构建对象需要有一个默认的可访问构造函数
	public MonitorDetail() {
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月");
		return "MonitorDetail [listingTime=" + f.format(new Date(listingTime)) + ", brand=" + brand + ", model=" + model
				+ ", size=" + size + ", toString()=" + super.toString() + "]";
	}

}
