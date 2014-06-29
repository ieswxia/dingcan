/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yizhiyun.test;

import com.yizhiyun.dingcan.Vendors;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

/**
 *
 * @author xiashiwen
 */
public class DingCanTest {
    public static void main(String args[]){
        Vendors vendor = new Vendors();
        vendor.setVendorId(1);
        vendor.setLatitude(39.32222);
        vendor.setLongitude(110.121212);
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Vendors firstVendor = (Vendors)session.get(Vendors.class, 0);
        Query selectUnique = session.createQuery("");
        if(null != firstVendor) {
//            firstVendor.setRestaurantName("剑客333");
            System.out.println("first vendor restaurant name :"+firstVendor.getRestaurantName());
            firstVendor.setRestaurantAddress("china beijing .");
            session.update(firstVendor);
        }
        session.getTransaction().commit();
//        session.close();
//        session.flush();
//        sessionFactory.close();
    }
}
