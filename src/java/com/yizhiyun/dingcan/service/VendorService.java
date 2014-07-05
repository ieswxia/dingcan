/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yizhiyun.dingcan.service;

import com.yizhiyun.dingcan.dao.BaseDao;
import com.yizhiyun.dingcan.model.Customer;
import com.yizhiyun.dingcan.model.Vendor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sjqi
 */
@Service
@Transactional
public class VendorService {
    @Autowired
    BaseDao baseDao;
    public void saveVendor(Vendor vendor){
        baseDao.save("VendorMapper.saveVendor", vendor);
    }
    
    public List<Vendor> getVendorsByTel(String telNo){
        return baseDao.getList("VendorMapper.getVendorsByTel", telNo);
    }

    public List<Vendor> getVendorsByCoordinate(float minLatitude, float maxLatitude, float minLongitude, float maxLongitude) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("minLatitude", minLatitude);
        params.put("minLongitude", minLongitude);
        params.put("maxLatitude", maxLatitude);
        params.put("maxLongitude", maxLongitude);
        return baseDao.getList("VendorMapper.getVendorsByCoordinate", params);
    }
}
