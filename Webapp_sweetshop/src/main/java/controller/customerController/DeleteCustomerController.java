package controller.customerController;

import dal.Customer.CustomerProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;

import java.io.IOException;

@WebServlet (name = "DeleteCustomerController", value = {"/deletecustomer"})
public class DeleteCustomerController extends HttpServlet {


    private CustomerProcess customerProcess;

    @Override
    public void init() {
        customerProcess = new CustomerProcess();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/getcustomer").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID của khách hàng từ tham số yêu cầu
        String customerId = request.getParameter("id");

        // Kiểm tra nếu ID không hợp lệ (null hoặc rỗng)
        if (customerId == null || customerId.trim().isEmpty()) {
            // Nếu ID rỗng, chuyển hướng đến trang danh sách với thông báo lỗi
            response.sendRedirect("getcustomer?error=Invalid customer ID");
            return; // Dừng việc xử lý thêm
        }

        try {
            CustomerProcess customerProcess = new CustomerProcess();

            // Tìm khách hàng với ID đã cho
            Customer customer = customerProcess.getCustomerById(customerId);

            // Kiểm tra sự tồn tại của khách hàng
            if (customer == null) {
                // Nếu không tìm thấy khách hàng, chuyển hướng đến trang danh sách với thông báo lỗi
                response.sendRedirect("getcustomer?error=Customer not found");
            } else {
                // Nếu tìm thấy khách hàng, tiến hành khóa khách hàng
                boolean result = customerProcess.lockCustomer(customerId);

                // Kiểm tra kết quả khóa khách hàng
                if (result) {
                    // Nếu khóa thành công, chuyển hướng đến trang danh sách khách hàng với thông báo thành công
                    response.sendRedirect("getcustomer?message=Customer locked successfully");
                } else {
                    // Nếu khóa thất bại, chuyển hướng đến trang danh sách với thông báo lỗi
                    response.sendRedirect("getcustomer?error=Failed to lock customer");
                }
            }
        } catch (Exception e) {
            // Bắt bất kỳ lỗi nào xảy ra và chuyển hướng đến trang danh sách với thông báo lỗi
            e.printStackTrace();
            response.sendRedirect("getcustomer?error=An unexpected error occurred while locking the customer");
        }
    }
}
