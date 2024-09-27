package controller.auth;

import dal.user.UserProcess;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import session.SesionRepo;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/auth/login")
public class LoginController extends HttpServlet {

//    @Override
//    public void init() throws ServletException {
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/page/auth/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nameOrEmail = req.getParameter("nameOrEmail").trim();
            String password = req.getParameter("password").trim();

            req.setAttribute("nameOrEmail", nameOrEmail);
            req.setAttribute("password", password);

            //Viết điều kiện kiểm tra các thông tin nhập vào....

            User user = UserProcess.Instance.loadUser(nameOrEmail, password);
            if (user != null) {// tìm thấy user
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                //đăng nhập thành công, xóa các thuộc tính đã thiết lập
                req.removeAttribute("nameOrEmail");
                req.removeAttribute("password");

                //đăng nhập vs role customer
                if(user.getRole() == 1 ){
                    resp.sendRedirect(req.getContextPath() + "/home");
                    return;
                }
                //đăng nhập vs role staff- sua lại link chuyển đến
                if(user.getRole() == 2 ){
                    resp.sendRedirect(req.getContextPath() + "/getstaff");
                    return;
                }
                //đăng nhập vs role shipper- sua lại link chuyển đến
                if(user.getRole() == 3 ){
                    resp.sendRedirect(req.getContextPath() + "/getstaff");
                    return;
                }
                //đăng nhập vs role admin- sua lai link chuyen den
                if(user.getRole() == 4 ){
                    resp.sendRedirect(req.getContextPath() + "/getstaff");
                    return;
                }
            } else {// KO tìm thấy user
                req.setAttribute("message", "Incorrect login information");
            }

        }catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("message", "An error occurred: " + ex.getMessage());
        }
        req.getRequestDispatcher("/page/auth/login.jsp").forward(req,resp);

    }
}