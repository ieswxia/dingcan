/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.dingcan.controller;

import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.model.Vendor;
import com.yizhiyun.dingcan.service.CustomerService;
import com.yizhiyun.dingcan.service.VendorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VendorService vendorService;

    @RequestMapping("/test")
    public @ResponseBody
    String test() {
        return "HelloWorld!";
    }

    @RequestMapping("/CreateCustomer")
    public @ResponseBody
    Map<String, Object> createCustom(@RequestParam("customerInfo") String customerInfoString) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == customerInfoString || customerInfoString.length() < 1) {
            map.put("error_code", "error_parameter_miss");
        } else {
            JSONObject customer = JSONObject.fromObject(customerInfoString);
            if (null == customer) {
                map.put("error_code", "error_parameter_parse_error");
            } else {
                Customer cust = (Customer) JSONObject.toBean(customer, Customer.class);
                String telephone = cust.getCustomerTel();

                boolean isError = false;

                List<Customer> customers = customerService.getCustomersByTel(telephone);

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
        }
        return map;
    }

    @RequestMapping("/CreatVendor")
    public @ResponseBody
    Map<String, Object> createVendor(@RequestParam("vendorInfo") String vendorString) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == vendorString || vendorString.length() < 1) {
            map.put("error_code", "error_parameter_miss");
        } else {
            JSONObject vendorJson = JSONObject.fromObject(vendorString);
            if (null == vendorJson) {
                map.put("error_code", "error_parameter_parse_error");
            } else {
                Vendor vendor = (Vendor) JSONObject.toBean(vendorJson, Vendor.class);
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
        }
        return map;
    }

    @RequestMapping("/GetNearVendors")
    public @ResponseBody
    Map<String, Object> getNearVendors(@RequestBody Map<String, Object> queryCondition) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (queryCondition == null) {
            result.put("error_code", "error_parameter_miss");
            return result;
        }
        Object location = queryCondition.get("customerLocationInfo");
        Object queryRadius = queryCondition.get("queryRadius");
        if (location == null || queryRadius == null || !location.getClass().isAssignableFrom(Map.class)) {
            result.put("error_code", "error_parameter_miss");
        }
        try {
            Map<String, Float> locationInfo = (Map<String, Float>) location;
            float queryRadiusNum = (Float) queryRadius;
            float latitude = locationInfo.get("my_latitude");
            float longitude = locationInfo.get("my_longitude");
            float minLatitude = latitude - queryRadiusNum;
            float maxLatitude = latitude + queryRadiusNum;
            float minLongitude = longitude - queryRadiusNum;
            float maxLongitude = longitude + queryRadiusNum;
            List<Vendor> vendors = vendorService.getVendorsByCoordinate(minLatitude, maxLatitude, minLongitude, maxLongitude);
            result.put("vendors", vendors);
            result.put("error_code", "error_success");
        } catch (Exception e) {
            result.put("error_code", "error_parameter_parse_error");
        }
        return result;
    }
}
