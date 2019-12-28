package controller;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session_bean.CategoryFacade;
import session_bean.ProductFacade;
import entity.*;
import static java.lang.Integer.parseInt;
import java.util.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import session_bean.CustomerFacade;
import session_bean.CustomerOrderFacade;
import session_bean.OrderManager;
import session_bean.OrderedProductFacade;
import session_bean.ProductDetailFacade;

@WebServlet(name = "ControllerServlet", loadOnStartup = 1,
        urlPatterns = {"/ControllerServlet",
            "/index",
            "/category",
            "/product",
            "/addToCart",
            "/viewCart",
            "/updateCart",
            "/purchase",
            "/checkout",
            "/checkoutAd",
            "/checkoutOrder",
            "/delete",
            "/edit",
            "/editCategory",
            "/deleteCategory",
            "/addProduct2",
            "/updateQuantity",
            "/customerInfo",
            "/customerOrdering",
            "/customerPurchased"})

public class ControllerServlet extends HttpServlet {

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
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        // store new product list in servlet context
        getServletContext().setAttribute("newProducts",
                productSB.findRange(new int[]{0, 4}));
        getServletContext().setAttribute("newCategories", categorySB.findAll());
    }

    private boolean validateForm(String name, String email, String phone, String address, String cityRegion, String ccNumber, HttpServletRequest request) {
        return false;
    }

    //private OrderManager orderManager;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        if (userPath.equals("/index")) {
            getServletContext().setAttribute("newProducts",
                productSB.findRange(new int[]{0, 4}));
            getServletContext().setAttribute("newCategories", categorySB.findAll());
        } 
        if (userPath.equals("/category")) {
            String categoryId = request.getQueryString();
            if (categoryId != null) {
                Category selectedCategory;
                List<Product> categoryProducts;
                selectedCategory
                        = categorySB.find(Integer.parseInt(categoryId));
                session.setAttribute("selectedCategory", selectedCategory);
                categoryProducts = (List<Product>) selectedCategory.getProductCollection();
                session.setAttribute("categoryProducts", categoryProducts);
            }
        } else if (userPath.equals("/product")) {
            String productId = request.getQueryString();
            if (productId != null) {
                Product selectedProduct;
                ProductDetail selectedProductDetail;
                selectedProduct
                        = productSB.find(Integer.parseInt(productId));
                selectedProductDetail
                        = productDetailSB.find(Integer.parseInt(productId));
                session.setAttribute("selectedProduct", selectedProduct);
                session.setAttribute("selectedProductDetail", selectedProductDetail);
            }
        } else if (userPath.equals("/viewCart")) {
            String clear = request.getParameter("clear");
            if ((clear != null) && clear.equals("true")) {
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                cart.clear();
            }
        } else if (userPath.equals("/addToCart")) {
            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            // get user input from request
            String productId = request.getQueryString();
            if (!productId.isEmpty()) {
                Product product
                        = productSB.find(Integer.parseInt(productId));
                cart.addItem(product);
            }
            String userView = (String) session.getAttribute("view");
            userPath = userView;
        } else if (userPath.equals("/checkout")) {
            Integer user_mode = (Integer) request.getSession().getAttribute("user_mode");
            Integer admin_mode = (Integer) request.getSession().getAttribute("admin_mode");

            if ((user_mode == null || user_mode == 0) && (admin_mode == null || admin_mode == 0)) {
                userPath = "login";
            } else {
                userPath = "checkout";
            }

        } else if (userPath.equals("/checkoutAd")) {
            String num = request.getQueryString();
            int index = Integer.parseInt(num);
            List<CustomerOrder> customerOrders = (List<CustomerOrder>) session.getAttribute("customerOrders");
            CustomerOrder customerOrder = customerOrders.get(index);
            customerOrder.setCheckoutAdmin(true);
            customerOrderSB.edit(customerOrder);
            
            session.setAttribute("customerOrders", customerOrders);
//            session.setAttribute("orderedProductss", orderedProductss);
//            session.setAttribute("productss", productss);
//            session.setAttribute("customers", customers);
            request.getRequestDispatcher("CheckoutAdminServlet").forward(request, response);
        } else if (userPath.equals("/addProduct2")) {
            String categoryId = request.getQueryString();
            Category category
                    = categorySB.find(Integer.parseInt(categoryId));
            session.setAttribute("category", category);
            userPath = "/addProduct";
        } else if (userPath.equals("/delete")) {
            String productId = request.getQueryString();
            Product product
                    = productSB.find(Integer.parseInt(productId));
            List<OrderedProduct> orderedProducts
                    = orderedProductSB.findAll();

            boolean flag = true;
            for (OrderedProduct op : orderedProducts) {
                if (op.getOrderedProductPK().getProductId() == product.getProductId()) {
                    flag = false;
                    break;
                }
            }
            if (product.getQuantity() == 0 && flag) {
                productSB.remove(product);
                Category category = product.getCategoryId();
            }
            List<Product> allProducts = productSB.findAll();
            session.setAttribute("allProducts", allProducts);

            //categorySB.getEntityManager().refresh(category);
            //List<Product> allProducts = (List<Product>) session.getAttribute("allProducts");
            //allProducts.remove(product);
            //session.setAttribute("allProducts", allProducts);*/
            userPath = "/allProducts";
        } else if (userPath.equals("/edit")) {
            String productId = request.getQueryString();
            Product product
                    = productSB.find(Integer.parseInt(productId));
            ProductDetail productDetail
                    = productDetailSB.find(Integer.parseInt(productId));
            session.setAttribute("editProduct", product);
            session.setAttribute("editProductDetail", productDetail);
            userPath = "/editProduct";
        } else if (userPath.equals("/deleteCategory")) {
            String categoryId = request.getQueryString();
            Category category
                    = categorySB.find(Integer.parseInt(categoryId));
            List<Product> products = productSB.findAll();
            boolean flag = true;
            for (Product product : products) {
                if (product.getCategoryId().getCategoryId() == category.getCategoryId()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                categorySB.remove(category);
            }
//            session.setAttribute("newCategories",category);
            userPath = "/index";
            response.sendRedirect("index");
        } else if (userPath.equals("/editCategory")) {
            String categoryId = request.getQueryString();
            Category category
                    = categorySB.find(Integer.parseInt(categoryId));
            session.setAttribute("editCategory", category);
            userPath = "/editCategory";
        } else if (userPath.equals("/updateQuantity")) {
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");
            Product product = productSB.find(Integer.parseInt(productId));
            product.setQuantity(Integer.parseInt(quantity));
            productSB.edit(product);
            List<Product> allProducts = productSB.findAll();
            session.setAttribute("allProducts", allProducts);
            userPath = "/quantityProduct";
        } else if (userPath.equals("/customerInfo")) {
            String customerId = request.getQueryString();
            Customer customer
                    = customerSB.find(Integer.parseInt(customerId));
            session.setAttribute("customerAD", customer);
            userPath = "/customerInfo";
        } else if (userPath.equals("/customerOrdering")) {
            String customerId = request.getQueryString();
            Customer customer
                    = customerSB.find(Integer.parseInt(customerId));
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("/OrderingServlet").forward(request, response);
        } else if (userPath.equals("/customerPurchased")) {
            String customerId = request.getQueryString();
            Customer customer
                    = customerSB.find(Integer.parseInt(customerId));
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("/PurchasedServlet").forward(request, response);
        }
        String url = userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        boolean validationErrorFlag = false;

        if (userPath.equals("/updateCart")) {
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");
            Product product
                    = productSB.find(Integer.parseInt(productId));
            if (Integer.parseInt(quantity) > product.getQuantity()) {
                quantity = product.getQuantity().toString();
            }
            cart.update(product, quantity);
            userPath = "/viewCart";
        } else if (userPath.equals("/purchase")) {

            int user_mode = (int) session.getAttribute("user_mode");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String cityRegion = request.getParameter("cityRegion");
            String ccNumber = request.getParameter("creditcard");

            if (name == null || email == null || phone == null || address == null || ccNumber == null) {
                session.setAttribute("error", "y");
                userPath = "/checkout";
            } else {
                Customer customer = (Customer) session.getAttribute("customer");
                if (customer == null) {
                    List<Customer> customers = customerSB.findAll();
                    for (Customer customer1 : customers) {
                        if (customer1.getEmail().equals(email)) {
                            customer = customer1;
                            break;
                        }
                    }
                }
                customerSB.edit(customer);
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomerId(customer);
                customerOrder.setAmount(cart.getSubtotal());

                Random random = new Random();
                int i = random.nextInt(999999999);
                customerOrder.setConfirmationNumber(i);
                customerOrder.setOrderId(i);
                customerOrder.setDateCreated(new Date());
                customerOrderSB.create(customerOrder);

                List<ShoppingCartItem> items = cart.getItems();
                for (ShoppingCartItem scItem : items) {
                    Product product = scItem.getProduct();
                    product.setQuantity(product.getQuantity() - scItem.getQuantity());
                    productSB.edit(product);
                    int productId = product.getProductId();
                    // set up primary key object
                    OrderedProductPK orderedProductPK = new OrderedProductPK();
                    orderedProductPK.setOrderId(customerOrder.getOrderId());
                    orderedProductPK.setProductId(productId);
                    // create ordered item using PK object
                    OrderedProduct orderedItem = new OrderedProduct(orderedProductPK);
                    // set quantity
                    orderedItem.setQuantity(scItem.getQuantity());
                    orderedProductSB.create(orderedItem);
                }

                int orderId = customerOrder.getOrderId();
                // get order details
                //Map orderMap = new HashMap();
                // get all ordered products
                List<OrderedProduct> orderedProducts
                        = orderedProductSB.findByOrderId(orderId);
                // get product details for ordered items
                List<Product> products = new ArrayList<Product>();
                for (OrderedProduct op : orderedProducts) {
                    Product p = (Product) productSB.find(op.getOrderedProductPK().getProductId());
                    products.add(p);
                }
                //cart = null;
                //session.invalidate();
                session.setAttribute("orderRecord", customerOrder);
                session.setAttribute("customer", customer);
                session.setAttribute("orderedProducts", orderedProducts);
                session.setAttribute("products", products);
                session.setAttribute("cart", null);

                userPath = "/confirmation";
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
