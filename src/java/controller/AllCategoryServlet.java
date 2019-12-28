package controller;

import entity.Account;
import entity.Category;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.CategoryFacade;
import session_bean.ProductFacade;

public class AllCategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @EJB
    CategoryFacade categorySB;

    public AllCategoryServlet() {
        super();
    }

    // Hiển thị trang Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Category> allCategories = categorySB.findAll();
        session.setAttribute("allCategories", allCategories);
        
        request.getRequestDispatcher("allCategories.jsp").forward(request, response);
    }

    // Khi người nhập userName & password, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
