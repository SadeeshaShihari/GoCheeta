/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.sadeesha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("Password");
        
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GoCheetaCab?useSSL=false", "root", "");
            PreparedStatement pst = con.prepareStatement("insert into customer(Username, Email, Password, Role) values(?,?,?,?) ");
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, "Customer");

            int count = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("registration.jsp");

            if (count > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }

            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();}
//        } finally {
//
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }

    }
}
