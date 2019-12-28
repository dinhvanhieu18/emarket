/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Category;
import entity.Product;
import entity.ProductDetail;
import javax.servlet.http.HttpSession;
import session_bean.CategoryFacade;
import session_bean.ProductDetailFacade;
import session_bean.ProductFacade;

/**
 *
 * @author kelvi
 */
public class AddProductServlet extends HttpServlet {

    @EJB
    private CategoryFacade categorySB;
    @EJB
    private ProductFacade productSB;
    @EJB
    private ProductDetailFacade productDetailSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String image = (String) request.getParameter("image");
        String name = (String) request.getParameter("name");
        String description = (String) request.getParameter("description");
        String description_detail = (String) request.getParameter("description_detail");
        String category_name = (String) request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String acsessories = (String) request.getParameter("acsessories");
        String guaranty = (String) request.getParameter("guaranty");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product p = new Product();
        ProductDetail pd = new ProductDetail();
        List<Category> categories = categorySB.findAll();
        Category category = new Category();
        for (Category categoryy: categories){
            if (categoryy.getName().equals(category_name)){
                category = categorySB.find(categoryy.getCategoryId());
                break;
            }
        }
        
        Random random = new Random();
        int product_id = random.nextInt(999999999);
        p.setProductId(product_id);
        p.setImage(image);
        p.setName(name);
        p.setCategoryId(category);
        p.setQuantity(quantity);
        p.setDescription(description);
        p.setDescriptionDetail(description_detail);
        p.setLastUpdate(new java.util.Date());
        p.setPrice(price);
        productSB.create(p);
        /*List<Product> allProducts = (List<Product>) session.getAttribute("allProducts");
        allProducts.add(p);
        session.setAttribute("allProducts", allProducts);*/
        //session.setAttribute("", name);
        pd.setProductId(product_id);
        pd.setImage1(image);
        pd.setInformation(description);
        pd.setAccessories(acsessories);
        pd.setGuaranty(guaranty);
        productDetailSB.create(pd);
        
        List<Product> products = productSB.findAll();
        List<Category> allCategories = categorySB.findAll();
        session.setAttribute("allProducts", products);
        session.setAttribute("allCategories", allCategories);
//        response.sendRedirect("index");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
