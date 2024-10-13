package controller.auth;

import dal.user.UserProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "forget", value = "/forget_password")
public class ForgetPass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/forgetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (UserProcess.Instance.findByEmail(email) != null) {
            response.sendRedirect(request.getContextPath() + "/check_otp?email=" + email);
        } else {
            request.setAttribute("email", email);
            request.setAttribute("message", "Email is not found");
            doGet(request, response);
        }
    }
}
