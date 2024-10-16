package controller.staffController;

import dal.Customer.CustomerProcess;
import dal.staff.StaffProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.Staff;

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
        // Lấy ID của nhân viên từ tham số yêu cầu
        String staffId = request.getParameter("id");

        // Kiểm tra nếu ID không hợp lệ (null hoặc rỗng)
        if (staffId == null || staffId.trim().isEmpty()) {
            // Nếu ID rỗng, chuyển hướng đến trang danh sách với thông báo lỗi
            response.sendRedirect("getstaff?error=Invalid staff ID");
            return; // Dừng việc xử lý thêm
        }

        try {
            StaffProcess staffProcess = new StaffProcess();

            // Tìm nhân viên với ID đã cho
            Staff staff = staffProcess.getStaffById(staffId);

            // Kiểm tra sự tồn tại của nhân viên
            if (staff == null) {
                // Nếu không tìm thấy nhân viên, chuyển hướng đến trang danh sách với thông báo lỗi
                response.sendRedirect("getstaff?error=Staff not found");
            } else {
                // Nếu tìm thấy nhân viên, tiến hành khóa nhân viên
                boolean result = staffProcess.lockStaff(staffId);

                // Kiểm tra kết quả khóa nhân viên
                if (result) {
                    // Nếu khóa thành công, chuyển hướng đến trang danh sách nhân viên với thông báo thành công
                    response.sendRedirect("getstaff?message=Staff locked successfully");
                } else {
                    // Nếu khóa thất bại, chuyển hướng đến trang danh sách với thông báo lỗi
                    response.sendRedirect("getstaff?error=Failed to lock staff");
                }
            }
        } catch (Exception e) {
            // Bắt bất kỳ lỗi nào xảy ra và chuyển hướng đến trang danh sách với thông báo lỗi
            e.printStackTrace();
            response.sendRedirect("getstaff?error=An unexpected error occurred while locking the staff");
        }
    }
}
