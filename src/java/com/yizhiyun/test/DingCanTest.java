/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.test;

import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.model.CustomerOrder;
import com.yizhiyun.dingcan.model.Food;
import com.yizhiyun.dingcan.model.OrderInfo;
import com.yizhiyun.dingcan.model.OrderItem;
import com.yizhiyun.dingcan.model.Vendor;
import com.yizhiyun.dingcan.model.VendorOrder;
import com.yizhiyun.dingcan.model.VendorOrderInfo;
import com.yizhiyun.dingcan.service.CustomerService;
import com.yizhiyun.dingcan.service.FoodService;
import com.yizhiyun.dingcan.service.OrderService;
import com.yizhiyun.dingcan.service.VendorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author xiashiwen
 */
public class DingCanTest {

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:/applicationContext-service.xml");
		// CustomerService customerService =
		// context.getBean(CustomerService.class);
		// Customer customer = new Customer(0, "河南省郑州市郑大一附院", "178238324234",
		// "李先生", "178238324234", "178238324234", "178238324234");
		// customerService.saveCustomer(customer);

		// VendorService venderService = context.getBean(VendorService.class);
		// Vendor vendor = new Vendor(0, "金水路222号", "178238324234", "郑州A府",
		// "178238324234", "178238324234", "178238324234", "178238324234",
		// "178238324234", 49.2132, 121.23322);
		// List<Vendor> vendors = venderService.getVendorsByCoordinate(49, 50,
		// 121, 122);
		// for(Vendor vendor : vendors){
		// System.out.println(vendor.getRestaurantName());
		// }

		// FoodService foodService = context.getBean(FoodService.class);
		// Food food = new Food(0, "辣子鸡丁", 20.0F, 1, "1");
		// foodService.saveFood(food);
		OrderService orderService = context.getBean(OrderService.class);
		OrderInfo orderInfo = new OrderInfo();
		CustomerOrder customerOrder = new CustomerOrder(0L, 2, "睁大一附院2L",
				"178238324234", "1", null);
		orderInfo.setCustomerOrder(customerOrder);
		List<VendorOrderInfo> vendorOrderInfos = new ArrayList<VendorOrderInfo>();
		VendorOrderInfo vendorOrderInfo = new VendorOrderInfo();
		VendorOrder vendorOrder = new VendorOrder(0, 1, null, null);
		vendorOrderInfo.setVendorOrder(vendorOrder);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(new OrderItem(0, null, 2, 1, "1"));
		vendorOrderInfo.setOrderItems(orderItems);
		vendorOrderInfos.add(vendorOrderInfo);
		orderInfo.setVendorOrders(vendorOrderInfos);
		orderService.order(orderInfo);
	}
}
