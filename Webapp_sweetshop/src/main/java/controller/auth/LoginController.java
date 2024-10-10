package controller.auth;

import dal.user.UserProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import session.SessionRepo;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("page/auth/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("btnLogin") != null) {
                login(req, resp);
            } else {
                register(req, resp);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("message", "An error occurred: " + ex.getMessage());
            doGet(req,resp);
        }
    }

    /**
     * login to system
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("nameOrEmail").trim();
        String password = request.getParameter("password").trim();

        User user = UserProcess.Instance.loadUser(username, password);

        if (user != null) {
            SessionRepo.setUser(request, response, user);
            switch (user.getRole()) {
                case(2) : // role staff
                    response.sendRedirect(request.getContextPath() + "/getstaff");
                    break;
                case(3) : // role shipper
                    response.sendRedirect(request.getContextPath() + "/getstaff");
                    break;
                case(4) : // role admin
                    response.sendRedirect(request.getContextPath() + "/getstaff");
                    break;
                default: // role customer
                    response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.setAttribute("message", "Incorrect login information");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            doGet(request,response);
        }
    }

    /**
     * register to login system
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void register (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}