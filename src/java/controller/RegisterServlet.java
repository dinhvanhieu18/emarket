package controller;

import entity.Account;
import entity.Customer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session_bean.AccountFacade;
import session_bean.CustomerFacade;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    @EJB
    AccountFacade accountSB;
    @EJB
    CustomerFacade customerSB;

    // Hiển thị trang Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward tới trang /WEB-INF/views/loginView.jsp
        // (Người dùng không thể truy cập trực tiếp
        // vào các trang JSP đặt trong thư mục WEB-INF).
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("login.jsp");

        dispatcher.forward(request, response);

    }

    // Khi người nhập userName & password, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)) {
            Account account
                    = accountSB.find(email);
            if ((email.equals("admin") && password.equals("123456")) || (account != null)) {
                session.setAttribute("user_mode", 0);
                session.setAttribute("admin_mode", 0);
                session.setAttribute("errRegister", "y");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                Account user = new Account(email, password);
                accountSB.create(user);
                Random random = new Random();
                int customer_id = random.nextInt(999999999);
                Customer customer = new Customer(customer_id);
                customer.setEmail(email);
                customerSB.create(customer);
                //session.setAttribute("user_mode", 1);
                //session.setAttribute("admin_mode", 0);
                //session.setAttribute("user", user);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            session.setAttribute("user_mode", 0);
            session.setAttribute("admin_mode", 0);
            session.setAttribute("errConfirmPass", "y");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }
}
