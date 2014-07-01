/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.dingcan.controller;

import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.service.CustomerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sjqi
 */
@Controller
public class MainController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/test")
    public @ResponseBody
    String test() {
        return "HelloWorld!";
    }

    @RequestMapping("/CreateCustomerServlet")
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
}
