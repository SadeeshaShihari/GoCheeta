/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.sadeesha;

import com.sadeesha.bean.LoginBean;
import com.sadeesha.dao.LoginDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uName = request.getParameter("email");
        String pass = request.getParameter("password");

        LoginBean loginBean = new LoginBean();

        loginBean.setEmail(uName);
        loginBean.setPassword(pass);

        LoginDao loginDao = new LoginDao();

        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = null;

        try {
            String userValidate = loginDao.authernticatedUser(loginBean);

            switch (userValidate) {
                case "Admin_Role":
                    session.setAttribute("name", loginBean.getUsername());
                    dispatcher = request.getRequestDispatcher("adminPortal.jsp");
                    break;
                case "User_Role":
                    session.setAttribute("name", loginBean.getUsername());
                    dispatcher = request.getRequestDispatcher("customerPortal.jsp");
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("index.html");
                    break;
            }
            dispatcher.forward(request, response);

        } catch (IOException | ServletException ex) {
        }

    }

}
