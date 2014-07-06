/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.dingcan.controller;

import com.google.gson.Gson;
import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.model.EditFoodInfo;
import com.yizhiyun.dingcan.model.Food;
import com.yizhiyun.dingcan.model.LocationInfo;
import com.yizhiyun.dingcan.model.OrderInfo;
import com.yizhiyun.dingcan.model.QueryLocation;
import com.yizhiyun.dingcan.model.Vendor;
import com.yizhiyun.dingcan.model.VendorOrderResult;
import com.yizhiyun.dingcan.service.CustomerService;
import com.yizhiyun.dingcan.service.FoodService;
import com.yizhiyun.dingcan.service.OrderService;
import com.yizhiyun.dingcan.service.VendorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author sjqi
 */
@Controller()
public class MainController {

	private Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private FoodService foodService;

	@Autowired
	private OrderService orderService;

	private Gson gson = new Gson();

	@RequestMapping("/test")
	public @ResponseBody
	String test() {
		return "HelloWorld!";
	}

	@RequestMapping("/CreateCustomer")
	public @ResponseBody
	Map<String, Object> createCustom(
			@RequestParam("customerInfo") String customerInfoString) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == customerInfoString || customerInfoString.length() < 1) {
			map.put("error_code", "error_parameter_miss");
		} else {

			Customer cust = (Customer) gson.fromJson(customerInfoString,
					Customer.class);
			String telephone = cust.getCustomerTel();

			boolean isError = false;

			List<Customer> customers = customerService
					.getCustomersByTel(telephone);

			if (null == customers || customers.size() < 1) {
				customerService.saveCustomer(cust);
				map.put("execute_save", "true");
			}

			customers = customerService.getCustomersByTel(telephone);

			if (null == customers) {
				isError = true;
			} else {
				map.put("customersss", customers);
			}
			if (true == isError) {
				map.put("error_code", "error_query_false");
			} else {
				map.put("error_code", "error_success");
			}

		}
		return map;
	}

	@RequestMapping("/CreatVendor")
	public @ResponseBody
	Map<String, Object> createVendor(
			@RequestParam("vendorInfo") String vendorString) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == vendorString || vendorString.length() < 1) {
			map.put("error_code", "error_parameter_miss");
		} else {
			Vendor vendor = (Vendor) gson.fromJson(vendorString, Vendor.class);
			String telephone = vendor.getRestaurantTel();

			boolean isError = false;

			List<Vendor> vendors = vendorService.getVendorsByTel(telephone);

			if (null == vendors || vendors.size() < 1) {
				vendorService.saveVendor(vendor);
				map.put("execute_save", "true");
			}

			vendors = vendorService.getVendorsByTel(telephone);

			if (null == vendors) {
				isError = true;
			} else {
				map.put("customersss", vendors);
			}
			if (true == isError) {
				map.put("error_code", "error_query_false");
			} else {
				map.put("error_code", "error_success");
			}

		}
		return map;
	}

	@RequestMapping("/getNearVendors")
	public @ResponseBody
	Map<String, Object> getNearVendors(@RequestBody QueryLocation queryLocation) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (queryLocation == null) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		LocationInfo location = queryLocation.getCustomerLocationInfo();
		double longitudeDelta = queryLocation.getLongitudeDelta();
		double latitudeDelta = queryLocation.getLatitudeDelta();
		if (location == null || longitudeDelta == 0 || latitudeDelta == 0) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			double minLatitude = location.getLatitude() - latitudeDelta;
			double maxLatitude = location.getLatitude() + latitudeDelta;
			double minLongitude = location.getLongitude() - longitudeDelta;
			double maxLongitude = location.getLongitude() + longitudeDelta;
			List<Vendor> vendors = vendorService.getVendorsByCoordinate(
					minLatitude, maxLatitude, minLongitude, maxLongitude);
			result.put("vendors", vendors);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_parameter_parse_error");
		}
		return result;
	}
	
	/**
	 * 编辑食物
	 * @param foodInfo
	 * @return
	 */
	@RequestMapping("/EditFood")
	public @ResponseBody
	Map<String, Object> editFoods(@RequestBody EditFoodInfo foodInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (foodInfo == null || foodInfo.getVendorId() == 0) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			foodService.editFoodInfo(foodInfo);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_query_false");
		}
		return result;
	}

	/**
	 * 查询食物
	 * @param vendorId
	 * @return
	 */
	@RequestMapping("/QueryFoods")
	public @ResponseBody
	Map<String, Object> qeuryFoods(int vendorId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (vendorId == 0) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			List<Food> foods = foodService.getFoodsByVendor(vendorId);
			result.put("foods", foods);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_query_false");
		}
		return result;
	}

	/**
	 * 下订单
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping("/Order")
	public @ResponseBody
	Map<String, Object> order(@RequestBody OrderInfo orderInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (orderInfo == null) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			orderService.order(orderInfo);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_query_false");
		}
		return result;
	}
	
	/**
	 * 查找个人订单
	 * @param customerOrderCustomerId
	 * @param days
	 * @return
	 */
	@RequestMapping("/QueryCustomerOrders")
	public @ResponseBody
	Map<String, Object> queryCustormOrders(int customerOrderCustomerId, int days) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (customerOrderCustomerId == 0 || days == 0) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			List<OrderInfo> orderInfos = orderService.queryCustomerOrders(customerOrderCustomerId, days);
			result.put("orders", orderInfos);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_query_false");
		}
		return result;
	}

	@RequestMapping("/QueryVendorOrders")
	public @ResponseBody
	Map<String, Object> queryVenderOrders(int vendorOrderVendorId, int queryTimeInterval) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (queryTimeInterval == 0 || vendorOrderVendorId == 0) {
			result.put("error_code", "error_parameter_miss");
			return result;
		}
		try {
			List<VendorOrderResult> orderInfos = orderService.queryVenderOrders(vendorOrderVendorId, queryTimeInterval);
			result.put("orders", orderInfos);
			result.put("error_code", "error_success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.put("error_code", "error_query_false");
		}
		return result;
	}
}
