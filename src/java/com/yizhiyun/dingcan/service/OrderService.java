package com.yizhiyun.dingcan.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yizhiyun.dingcan.dao.BaseDao;
import com.yizhiyun.dingcan.model.CustomerOrder;
import com.yizhiyun.dingcan.model.OrderInfo;
import com.yizhiyun.dingcan.model.OrderItem;
import com.yizhiyun.dingcan.model.VendorOrder;
import com.yizhiyun.dingcan.model.VendorOrderInfo;
import com.yizhiyun.dingcan.model.VendorOrderResult;

@Service
@Transactional
public class OrderService {

	@Autowired
	private BaseDao baseDao;

	public void saveCustomerOrder(CustomerOrder customerOrder) {
		baseDao.save("OrderMapper.saveCustomerOrder", customerOrder);
	}

	public void saveVendorOrder(VendorOrder vendorOrder) {
		baseDao.save("OrderMapper.saveVendorOrder", vendorOrder);
	}

	public void saveOrderItem(OrderItem orderItem) {
		baseDao.save("OrderMapper.saveOrderItem", orderItem);
	}

	public List<CustomerOrder> getCustomerOrdersByCustomerId(
			int customerOrderCustomerId, int days) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerOrderCustomerId", customerOrderCustomerId);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0 - days);
		params.put("minDay", calendar.getTime());
		return baseDao.getList("OrderMapper.getCustomerOrdersByCustomerId",
				params);
	}
	
	public CustomerOrder getCustomerOrderById(
			long orderId) {
		return baseDao.get("OrderMapper.getCustomerOrderById",
				orderId);
	}

	public List<VendorOrder> getVendorOrdersByOrderId(long customerOrderId) {
		return baseDao.getList("OrderMapper.getVendorOrdersByOrderId",
				customerOrderId);
	}

	private List<OrderItem> getOrderItemsByVenderOrderId(long vendorOrderId) {
		return baseDao.getList("OrderMapper.getOrderItemsByVenderOrderId",
				vendorOrderId);
	}

	public void order(OrderInfo orderInfo) {
		CustomerOrder customerOrder = orderInfo.getCustomerOrder();
		if (customerOrder == null) {
			throw new RuntimeException("CustomerOrderNullException");
		}

		List<VendorOrderInfo> vendorOrders = orderInfo.getVendorOrders();

		if (vendorOrders == null || vendorOrders.size() == 0) {
			throw new RuntimeException("VendorOrdersNullException");
		}

		Date date = new Date();
		customerOrder.setCustomerOrderTime(date);
		saveCustomerOrder(customerOrder);

		for (VendorOrderInfo vendorOrderInfo : vendorOrders) {
			VendorOrder vendorOrder = vendorOrderInfo.getVendorOrder();
			if (vendorOrder == null) {
				throw new RuntimeException("VendorOrderNullException");
			}
			vendorOrder.setVendorOrderTime(date);
			vendorOrder.setVendorOrderCustomerOrderId(customerOrder
					.getCustomerOrderId());
			saveVendorOrder(vendorOrder);

			List<OrderItem> orderItems = vendorOrderInfo.getOrderItems();
			if (orderItems == null || orderItems.size() == 0) {
				throw new RuntimeException("VendorItemsNullException");
			}
			for (OrderItem orderItem : orderItems) {
				orderItem.setOrderItemVendorOrderId(vendorOrder
						.getVendorOrderId());
				saveOrderItem(orderItem);
			}
		}
	}

	public List<OrderInfo> queryCustomerOrders(int customerOrderCustomerId,
			int days) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		List<CustomerOrder> customerOrders = getCustomerOrdersByCustomerId(customerOrderCustomerId, days);
		for (CustomerOrder customerOrder : customerOrders) {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setCustomerOrder(customerOrder);
			List<VendorOrderInfo> vendorOrderInfos = new ArrayList<VendorOrderInfo>();
			List<VendorOrder> vendorOrders = getVendorOrdersByOrderId(customerOrder
					.getCustomerOrderId());
			for (VendorOrder vendorOrder : vendorOrders) {
				VendorOrderInfo vendorOrderInfo = new VendorOrderInfo();
				vendorOrderInfo.setVendorOrder(vendorOrder);
				List<OrderItem> orderItems = getOrderItemsByVenderOrderId(vendorOrder
						.getVendorOrderId());
				vendorOrderInfo.setOrderItems(orderItems);
				vendorOrderInfos.add(vendorOrderInfo);
			}
			orderInfo.setVendorOrders(vendorOrderInfos);
			orderInfos.add(orderInfo);
		}
		return orderInfos;
	}
	
	public List<VendorOrderResult> queryVenderOrders(int vendorOrderVendorId,
			int queryTimeInterval) {
		List<VendorOrderResult> result = new ArrayList<VendorOrderResult>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorOrderVendorId);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 0 - queryTimeInterval);
		params.put("minTime", calendar.getTime());
		List<VendorOrder> vendorOrders = baseDao.getList("OrderMapper.getVendorOrdersByVendorId", params);
		for (VendorOrder vendorOrder : vendorOrders) {
			VendorOrderResult vendorOrderResult = new VendorOrderResult();
			vendorOrderResult.setVendorOrder(vendorOrder);
			vendorOrderResult.setCustomerOrder(getCustomerOrderById(vendorOrder.getVendorOrderCustomerOrderId()));
			vendorOrderResult.setOrderItems(getOrderItemsByVenderOrderId(vendorOrder.getVendorOrderId()));
			result.add(vendorOrderResult);
		}
		return result;
	}

}
