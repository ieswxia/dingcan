/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dingcan.api;

import com.example.dingcan.Vendors;
import com.example.dingcan.HibernateSessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import net.sf.json.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author xiashiwen
 */
public class FindNearbyRestaurantsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String myLatitudeString = request.getParameter("my_latitude");
        String myLongitudeString = request.getParameter("my_longitude");

        if (null == myLatitudeString || null == myLongitudeString) {
            Map map = new HashMap();
            map.put("error_code", "error_parameter_miss");

            JSONObject jsonObject = JSONObject.fromObject(map);

            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.print(jsonObject.toString());
            } finally {
                out.close();
            }
            return;
        }

        double myLatitude = Double.parseDouble(myLatitudeString);
        double myLongitude = Double.parseDouble(myLongitudeString);

//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        SessionFactory sessionFactory = HibernateSessionFactory.sharedSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Vendors firstVendor = (Vendors) session.get(Vendors.class, 0);
        List<Vendors> list = new ArrayList<Vendors>();
        list.add(firstVendor);
        
        Map map = new HashMap();
        map.put("error_code", "error_success");
        map.put("vendors",list);

        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println(jsonObject);

//        String sql = "select a.* from tb_doc_catalog a where a.cat_code like '"++"%'";
//        try {
//            List catNameList = session.createSQLQuery(sql).addEntity(DocCatalogInfo.class).list();
//            return catNameList ;
//        } finally {
//            releaseSession(session); //释放session
//        }
        if (null != firstVendor) {
            System.out.println("first vendor restaurant name :" + firstVendor.getRestaurantName());
            firstVendor.setRestaurantAddress("china beijing .");
            session.update(firstVendor);
        }
        session.getTransaction().commit();

        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.print(jsonObject.toString());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
