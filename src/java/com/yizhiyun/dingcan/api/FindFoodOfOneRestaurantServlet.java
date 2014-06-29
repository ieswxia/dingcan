/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yizhiyun.dingcan.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhiyun.dingcan.HibernateSessionFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import net.sf.json.JSONObject;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.yizhiyun.dingcan.Food;
import org.hibernate.Transaction;
import org.hibernate.Query;

/**
 *
 * @author xiashiwen
 */
public class FindFoodOfOneRestaurantServlet extends HttpServlet {

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
        
        String vendorIdString = request.getParameter("vendorId");
        if(null == vendorIdString || vendorIdString.length() < 1 ) {
            Map map = new HashMap();
            map.put("error_code", "error_parameter_miss");

            JSONObject jsonObject = JSONObject.fromObject(map);
            PrintWriter out = response.getWriter();
            try {
                out.print(jsonObject.toString());
            } finally {
                out.close();
            }
        }else{
            int vendorId = Integer.parseInt(vendorIdString);
            Map map = new HashMap();
            
            boolean isError = false;
            
            SessionFactory sessff = HibernateSessionFactory.sharedSessionFactory();
            if(null == sessff){
                isError = true;
            }else{
                Session session = sessff.openSession();
//                Transaction transa = session.beginTransaction();
                Query query = session.createQuery("from Food as F where F.vendorId=?");
                query.setInteger(0, vendorId);
                List foods = query.list();
//                transa.rollback();
//                transa.commit();
                if(null == foods){
                    isError = true;
                }else{
                    map.put("foods", foods);
                }
                session.close();
            }
            if(true == isError) {
                map.put("error_code", "error_query_false");
            }else{
                map.put("error_code", "error_success");
            }
            
            
            JSONObject jsonObject = JSONObject.fromObject(map);
            PrintWriter out = response.getWriter();
            try {
                out.print(jsonObject.toString());
            } finally {
                out.close();
            }
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
