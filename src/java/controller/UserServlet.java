/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.AccountFacade;
import session_bean.CustomerFacade;

/**
 *
 * @author kelvi
 */
@WebServlet(name = "UserServlet",
        urlPatterns = {"/UserServlet",
            "/updateInfo",
            "/changePassword"})
public class UserServlet extends HttpServlet {

    @EJB
    private AccountFacade accountSB;
    @EJB
    private CustomerFacade customerSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("user");
//        List<Customer> customers = customerSB.findAll();
//
//        for (Customer customer : customers) {
//            if (customer.getEmail().equals(account.getEmail())) {
//                session.setAttribute("customer", customer);
//                break;
//            }
//        }

        try {
            request.getRequestDispatcher("userInfo.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        Customer customer = (Customer) session.getAttribute("customer");

        String userPath = request.getServletPath();

        if (userPath.equals("/updateInfo")) {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String cityRegion = request.getParameter("cityRegion");
            String ccNumber = request.getParameter("creditcard");

            customer.setName(name);
            customer.setPhone(phone);
            customer.setAddress(address);
            customer.setCityRegion(cityRegion);
            customer.setCcNumber(ccNumber);
            customerSB.edit(customer);
            session.setAttribute("customer", customer);

            userPath = "userInfo";
        } else if (userPath.equals("/changePassword")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String password2 = request.getParameter("password2");
            if (password.equals(account.getPassword())) {
                if (newPassword.equals(password2)) {
                    account.setPassword(newPassword);
                    accountSB.edit(account);
                    session.setAttribute("user", account);
                    userPath = "userInfo";
                } else {
                    session.setAttribute("errConfirm", "y");
                    userPath = "changePassword";
                }
            } else {
                session.setAttribute("errPass", "y");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }

        String url = userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
