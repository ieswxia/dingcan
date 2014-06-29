/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yizhiyun.dingcan;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author xiashiwen
 */
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory = null;

    public synchronized static SessionFactory sharedSessionFactory() {
        if (null == sessionFactory) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
