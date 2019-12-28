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

public class EditProductServlet extends HttpServlet {
    
    @EJB
    private ProductFacade productSB;
    @EJB
    private ProductDetailFacade productDetailSB;
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
        String description = (String) request.getParameter("description");
        String description_detail = (String) request.getParameter("description_detail");
        String category_name = (String) request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String acsessories = (String) request.getParameter("acsessories");
        String guaranty = (String) request.getParameter("guaranty");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product p = new Product();
        ProductDetail pd = new ProductDetail();
        Product editProduct = (Product) session.getAttribute("editProduct");
        p.setProductId(editProduct.getProductId());
        p.setImage(image);
        p.setName(name);
        p.setQuantity(quantity);
        List<Category> categories = categorySB.findAll();
        Category category = new Category();
        for (Category categoryy: categories){
            if (categoryy.getName().equals(category_name)){
                category = categorySB.find(categoryy.getCategoryId());
                break;
            }
        }
        p.setCategoryId(category);
        p.setDescription(description);
        p.setDescriptionDetail(description_detail);
        p.setLastUpdate(new java.util.Date());
        p.setPrice(price);
        productSB.edit(p);
        /*List<Product> allProducts = (List<Product>) session.getAttribute("allProducts");
         allProducts.add(p);
         session.setAttribute("allProducts", allProducts);*/

        pd.setProductId(editProduct.getProductId());
        pd.setImage1(image);
        pd.setInformation(description);
        pd.setAccessories(acsessories);
        pd.setGuaranty(guaranty);
        productDetailSB.edit(pd);
        List<Product> products = productSB.findAll();
        List<Category> allCategories = categorySB.findAll();
        session.setAttribute("allProducts", products);
        session.setAttribute("allCategories", allCategories);
        request.getRequestDispatcher("allProducts.jsp").forward(request, response);
    }

}
