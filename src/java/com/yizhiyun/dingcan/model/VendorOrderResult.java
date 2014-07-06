package com.yizhiyun.dingcan.model;

import java.util.List;

public class VendorOrderResult {
	private VendorOrder vendorOrder;
	
	private CustomerOrder customerOrder;
	
	private List<OrderItem> orderItems;

	public VendorOrder getVendorOrder() {
		return vendorOrder;
	}

	public void setVendorOrder(VendorOrder vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
