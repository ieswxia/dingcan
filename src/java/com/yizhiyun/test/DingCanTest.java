/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.test;

import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xiashiwen
 */
public class DingCanTest {

    public static void main(String args[]) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/applicationContext-service.xml");
        CustomerService customerService = context.getBean(CustomerService.class);
        Customer customer = new Customer(0, "河南省郑州市郑大一附院", "178238324234", "李先生", "178238324234", "178238324234", "178238324234");
        customerService.saveCustomer(customer);
    }
}
