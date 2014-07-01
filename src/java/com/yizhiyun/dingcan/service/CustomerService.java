/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yizhiyun.dingcan.service;

import com.yizhiyun.dingcan.dao.BaseDao;
import com.yizhiyun.dingcan.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sjqi
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    BaseDao baseDao;
    public void saveCustomer(Customer customer){
        baseDao.save("CustomerMapper.saveCustomer", customer);
    }
    
    public List<Customer> getCustomersByTel(String telNo){
        return baseDao.getList("CustomerMapper.getCustomersByTel", telNo);
    }
}
