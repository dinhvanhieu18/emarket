package controller;

import entity.Customer;
import entity.CustomerOrder;
import entity.OrderedProduct;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.CategoryFacade;
import session_bean.CustomerFacade;
import session_bean.CustomerOrderFacade;
import session_bean.OrderManager;
import session_bean.OrderedProductFacade;
import session_bean.ProductDetailFacade;
import session_bean.ProductFacade;

//@WebServlet(name = "CheckoutServlet",
//      urlPatterns = {"/CheckoutServlet",
//        "/checkoutAdmin"})
public class CheckoutAdminServlet extends HttpServlet {

    @EJB
    private CategoryFacade categorySB;
    @EJB
    private ProductFacade productSB;
    @EJB
    private ProductDetailFacade productDetailSB;
    @EJB
    private CustomerFacade customerSB;
    @EJB
    private CustomerOrderFacade customerOrderSB;
    @EJB
    private OrderedProductFacade orderedProductSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        List<CustomerOrder> customerOrderstmp
                = customerOrderSB.findAll();
        
        List<Customer> customers = new ArrayList<Customer>();
        List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
        List<List<OrderedProduct>> orderedProductss = new ArrayList<List<OrderedProduct>>();
        for (CustomerOrder customerOrder : customerOrderstmp) {
            if (customerOrder.isCheckoutAdmin()) {
                continue;
            }
            customerOrders.add(customerOrder);
            List<OrderedProduct> orderedProducts
                    = orderedProductSB.findByOrderId(customerOrder.getOrderId());
            Customer customer = customerOrder.getCustomerId();
            orderedProductss.add(orderedProducts);
            customers.add(customer);

        }
        List<List<Product>> productss = new ArrayList<List<Product>>();
        for (List<OrderedProduct> orderedProducts : orderedProductss) {
            List<Product> products = new ArrayList<Product>();
            for (OrderedProduct op : orderedProducts) {
                Product p = (Product) productSB.find(op.getOrderedProductPK().getProductId());
                products.add(p);
            }
            productss.add(products);
        }

        session.setAttribute("customerOrders", customerOrders);
        session.setAttribute("orderedProductss", orderedProductss);
        session.setAttribute("productss", productss);
        session.setAttribute("customers", customers);

        request.getRequestDispatcher("checkoutAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
