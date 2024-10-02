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

@WebServlet(name = "GetStaffDisableController", value = {"/staffdisable"})
public class GetStaffDisableController extends HttpServlet {

    private StaffProcess staffProcess;

    @Override
    public void init() {
        staffProcess = new StaffProcess(); // Tạo mới StaffProcess
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Staff> staffList = staffProcess.getStaffDisable(); // Lấy danh sách nhân viên không hoạt động
        request.setAttribute("staffs", staffList);
        request.getRequestDispatcher("page/admin/staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement any post request handling if needed
    }
}
