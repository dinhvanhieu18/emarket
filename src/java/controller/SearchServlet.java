/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.ProductFacade;

/**
 *
 * @author kelvi
 */
public class SearchServlet extends HttpServlet {
    
    @EJB
    ProductFacade productSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("nameProduct");
        List<Product> products
                = productSB.findAll();
        List<Product> productRes = new ArrayList<Product>();
        for (Product product : products){
            if (product.getName().toLowerCase().contains(name.toLowerCase())){
                productRes.add(product);
            }
        }
        session.setAttribute("productRes", productRes);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        }

    }
