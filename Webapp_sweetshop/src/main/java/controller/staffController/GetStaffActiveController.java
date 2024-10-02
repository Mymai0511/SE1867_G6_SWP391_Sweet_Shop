package controller.staffController;

import dal.staff.StaffProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Staff;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetStaffActiveController", value = {"/staffactive"})
public class GetStaffActiveController extends HttpServlet {
    private StaffProcess staffProcess;

    @Override
    public void init() {
        staffProcess = new StaffProcess(); // Initialize StaffProcess
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Staff> staffList = staffProcess.getStaffActive(); // Get active staff
        request.setAttribute("staffs", staffList);
        request.getRequestDispatcher("page/admin/staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle any post requests if needed
    }
}
