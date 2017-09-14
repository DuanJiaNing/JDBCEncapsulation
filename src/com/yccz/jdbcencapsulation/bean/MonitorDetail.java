package com.yccz.jdbcencapsulation.bean;

import java.util.Date;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * ��ʾ����ϸ��������ʵ����
 * ��Ӧ���ݿ��: monitor_detail
 * @author 2017/09/13 DuanJiaNing
 *
 */
@Mapping("monitor_detail")
public class MonitorDetail {

	/**
	 * ��Ʒ id
	 */
	@Mapping("id")
	int id;
	
	/**
	 * ����ʱ��
	 */
	@Mapping("listing_time")
	Date listingTime;
	
	/**
	 * Ʒ��
	 */
	@Mapping("brand")
	String brand;
	
	/**
	 * �ͺ�
	 */
	@Mapping("model")
	String model;
	
	/**
	 * ��Ļ�ߴ�
	 */
	@Mapping("size")
	String size;

	@Override
	public String toString() {
		return "MonitorDetail [listingTime=" + listingTime + ", brand=" + brand + ", model=" + model + ", size=" + size
				+ ", id=" + id + "]";
	}
	
	
}
