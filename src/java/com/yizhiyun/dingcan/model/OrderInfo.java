package com.yizhiyun.dingcan.model;

import java.util.List;

public class OrderInfo {
	private CustomerOrder customerOrder;
	
	private List<VendorOrderInfo> vendorOrders;

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public List<VendorOrderInfo> getVendorOrders() {
		return vendorOrders;
	}

	public void setVendorOrders(List<VendorOrderInfo> vendorOrders) {
		this.vendorOrders = vendorOrders;
	}
}
