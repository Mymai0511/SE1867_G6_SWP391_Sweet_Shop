package controller.staffController;

import dal.staff.StaffProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetStaffSearchController", value = {"/getstaffsearch"})
public class GetStaffSearchController extends HttpServlet {
    private List<Staff> staffList = new ArrayList<>();

    @Override
    public void init() {
        staffList = StaffProcess.Instance.read(); // Load danh sách staff ban đầu
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("staffs", staffList);
        request.getRequestDispatcher("page/admin/staff_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchKeyword = request.getParameter("search"); // Lấy từ khóa tìm kiếm từ request
        List<Staff> searchedStaffs = StaffProcess.Instance.searchStaff(searchKeyword); // Gọi phương thức tìm kiếm

        request.setAttribute("staffs", searchedStaffs); // Đưa danh sách staff đã tìm kiếm vào request
        request.getRequestDispatcher("page/admin/staff_list.jsp").forward(request, response); // Chuyển hướng về trang danh sách staff
    }
}
