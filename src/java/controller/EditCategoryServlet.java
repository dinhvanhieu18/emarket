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

public class EditCategoryServlet extends HttpServlet {
    

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
        Category category = (Category) session.getAttribute("editCategory");
        category.setImage(image);
        category.setName(name);
        
        categorySB.edit(category);

        request.getRequestDispatcher("allCategories.jsp").forward(request, response);
    }

}
