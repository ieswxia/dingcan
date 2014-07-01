package com.yizhiyun.dingcan.api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhiyun.dingcan.Customers;
import com.yizhiyun.dingcan.HibernateSessionFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author xiashiwen
 */
public class CreateCustomerServlet extends HttpServlet {

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

        Map map = new HashMap();

        String customerInfoString = request.getParameter("customerInfo");
        if (null == customerInfoString || customerInfoString.length() < 1) {

            map.put("error_code", "error_parameter_miss");

        } else {
            JSONObject customer = JSONObject.fromObject(customerInfoString);
            if (null == customer) {
                map.put("error_code", "error_parameter_parse_error");
            } else {
                Customers cust = (Customers)JSONObject.toBean(customer, Customers.class);
                
                String telephone = cust.getCustomerTel();

                boolean isError = false;

                SessionFactory sessff = HibernateSessionFactory.sharedSessionFactory();
                if (null == sessff) {
                    isError = true;
                } else {
                    Session session = sessff.openSession();
//                    Transaction transa = session.beginTransaction();
                    Query query = session.createQuery("from Customers as C where C.customerTel=?");
                    query.setString(0, telephone);
                    List customersss = query.list();
//                transa.rollback();
//                transa.commit();
                    if(null == customersss || customersss.size() < 1) {
                        session.save(cust);
                        session.flush();
                        map.put("execute_save", "true");
                    }
                    
                    customersss = query.list();
                    
                    if (null == customersss) {
                        isError = true;
                    } else {
                        map.put("customersss", customersss);
                    }
                    session.close();
                }
                if (true == isError) {
                    map.put("error_code", "error_query_false");
                } else {
                    map.put("error_code", "error_success");
                }
            }

        }

        JSONObject jsonObject = JSONObject.fromObject(map);
        PrintWriter out = response.getWriter();
        try {
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
