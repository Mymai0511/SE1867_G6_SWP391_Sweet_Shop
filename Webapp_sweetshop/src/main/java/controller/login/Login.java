package controller.login;

import dal.user.UserProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import session.SesionRepo;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/page/cakeMain/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserProcess.Instance.findUser(email, password);
        if (user != null) {
            SesionRepo.setUser(req,resp,user);
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}