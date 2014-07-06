package com.yizhiyun.dingcan.model;

// Generated Jun 11, 2014 11:44:43 PM by Hibernate Tools 3.6.0

import java.util.Date;

/**
 * VendorOrders generated by hbm2java
 */
public class VendorOrder {

	private long vendorOrderId;
	private Integer vendorOrderVendorId;
	private Long vendorOrderCustomerOrderId;
	private Date vendorOrderTime;

	public VendorOrder() {
	}

	public VendorOrder(long vendorOrderId) {
		this.vendorOrderId = vendorOrderId;
	}

	public VendorOrder(long vendorOrderId, Integer vendorOrderVendorId,
			Long vendorOrderCustomerOrderId, Date vendorOrderTime) {
		this.vendorOrderId = vendorOrderId;
		this.vendorOrderVendorId = vendorOrderVendorId;
		this.vendorOrderCustomerOrderId = vendorOrderCustomerOrderId;
		this.vendorOrderTime = vendorOrderTime;
	}

	public long getVendorOrderId() {
		return this.vendorOrderId;
	}

	public void setVendorOrderId(long vendorOrderId) {
		this.vendorOrderId = vendorOrderId;
	}

	public Integer getVendorOrderVendorId() {
		return this.vendorOrderVendorId;
	}

	public void setVendorOrderVendorId(Integer vendorOrderVendorId) {
		this.vendorOrderVendorId = vendorOrderVendorId;
	}

	public Long getVendorOrderCustomerOrderId() {
		return this.vendorOrderCustomerOrderId;
	}

	public void setVendorOrderCustomerOrderId(Long vendorOrderCustomerOrderId) {
		this.vendorOrderCustomerOrderId = vendorOrderCustomerOrderId;
	}

	public Date getVendorOrderTime() {
		return this.vendorOrderTime;
	}

	public void setVendorOrderTime(Date vendorOrderTime) {
		this.vendorOrderTime = vendorOrderTime;
	}

}
