package com.yizhiyun.dingcan.model;

import java.util.List;

public class VendorOrderInfo {
	private VendorOrder vendorOrder;
	
	private List<OrderItem> orderItems;

	public VendorOrder getVendorOrder() {
		return vendorOrder;
	}

	public void setVendorOrder(VendorOrder vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
