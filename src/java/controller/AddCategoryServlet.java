/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.Product;
import entity.ProductDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.CategoryFacade;
import session_bean.ProductDetailFacade;
import session_bean.ProductFacade;

public class AddCategoryServlet extends HttpServlet {
    

    @EJB
    private CategoryFacade categorySB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        String image = (String) request.getParameter("image");
        String name = (String) request.getParameter("name");
        Category category = new Category();
        Random random = new Random();
        int category_id = random.nextInt(999999999);
        category.setCategoryId(category_id);
        category.setImage(image);
        category.setName(name);
        
        categorySB.create(category);
//        List<Product> products = productSB.findAll();
        List<Category> allCategories = categorySB.findAll();
//        session.setAttribute("allProducts", products);
        session.setAttribute("allCategories", allCategories);
        
        response.sendRedirect("index");

//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
