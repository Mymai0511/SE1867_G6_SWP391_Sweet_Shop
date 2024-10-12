package controller.staffController;

import dal.staff.StaffProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet (name = "DeleteStaffController", value = {"/deletestaff"})
public class DeleteStaffController extends HttpServlet {


    private StaffProcess staffProcess;

    @Override
    public void init() {
        staffProcess = new StaffProcess();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/getstaff").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String staffId = request.getParameter("id").trim();

        }
        catch (Exception ex) {
        ex.printStackTrace();
        request.setAttribute("message", "An error occurred: " + ex.getMessage());
    }
        request.getRequestDispatcher("/getstaff").forward(request, response);
}
}
