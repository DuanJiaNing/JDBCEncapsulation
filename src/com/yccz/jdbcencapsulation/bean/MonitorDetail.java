package com.yccz.jdbcencapsulation.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yccz.jdbcencapsulation.Mapping;

/**
 * ��ʾ����ϸ��������ʵ���� ��Ӧ���ݿ��: monitor_detail
 * 
 * @author 2017/09/13 DuanJiaNing
 *
 */
@Mapping("monitor_detail")
public class MonitorDetail extends Data {

	/**
	 * ����ʱ��
	 */
	@Mapping("listing_time")
	private long listingTime;

	/**
	 * Ʒ��
	 */
	@Mapping("brand")
	private String brand;

	/**
	 * �ͺ�
	 */
	@Mapping("model")
	private String model;

	/**
	 * ��Ļ�ߴ�
	 */
	@Mapping("size")
	private float size;

	/**
	 * ָ���������ֵ����ͨ�����ڡ����¡����� id ������Ψһȷ��Ŀ��������ݣ���������Ϊ�µ�ֵ
	 */
	public MonitorDetail(int id, long listingTime, String brand, String model, float size) {
		super(id);
		this.listingTime = listingTime;
		this.brand = brand;
		this.model = model;
		this.size = size;
	}

	/**
	 * ����ָ�� id �Ĺ��캯������Ϊ id �����������Զ����� ��ͨ�����ڡ�����������
	 */
	public MonitorDetail(long listingTime, String brand, String model, float size) {
		this.listingTime = listingTime;
		this.brand = brand;
		this.model = model;
		this.size = size;
	}

	// ���乹��������Ҫ��һ��Ĭ�ϵĿɷ��ʹ��캯��
	public MonitorDetail() {
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy��MM��");
		return "MonitorDetail [listingTime=" + f.format(new Date(listingTime)) + ", brand=" + brand + ", model=" + model
				+ ", size=" + size + ", toString()=" + super.toString() + "]";
	}

}
