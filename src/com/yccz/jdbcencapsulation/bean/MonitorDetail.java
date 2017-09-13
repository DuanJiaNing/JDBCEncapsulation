package com.yccz.jdbcencapsulation.bean;

import java.util.Date;

/**
 * 显示器详细参数数据实体类
 * 对应数据库表: monitor_detail
 * @author 2017/09/13 DuanJiaNing
 *
 */
@Mapping("monitor_detail")
public class MonitorDetail extends Commodity {

	
	/**
	 * 上市时间
	 */
	@Mapping("listing_time")
	Date listingTime;
	
	/**
	 * 品牌
	 */
	@Mapping("brand")
	String brand;
	
	/**
	 * 型号
	 */
	@Mapping("model")
	String model;
	
	/**
	 * 屏幕尺寸
	 */
	@Mapping("size")
	String size;

	@Override
	public String toString() {
		return "MonitorDetail [listingTime=" + listingTime + ", brand=" + brand + ", model=" + model + ", size=" + size
				+ ", id=" + id + "]";
	}
	
	
}
