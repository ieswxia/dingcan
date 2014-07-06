package com.yizhiyun.dingcan.model;

public class QueryLocation {
	private LocationInfo customerLocationInfo;
	
	private double longitudeDelta;
	
	private double latitudeDelta;

	public LocationInfo getCustomerLocationInfo() {
		return customerLocationInfo;
	}

	public void setCustomerLocationInfo(LocationInfo customerLocationInfo) {
		this.customerLocationInfo = customerLocationInfo;
	}

	public double getLongitudeDelta() {
		return longitudeDelta;
	}

	public void setLongitudeDelta(double longitudeDelta) {
		this.longitudeDelta = longitudeDelta;
	}

	public double getLatitudeDelta() {
		return latitudeDelta;
	}

	public void setLatitudeDelta(double latitudeDelta) {
		this.latitudeDelta = latitudeDelta;
	}
}
