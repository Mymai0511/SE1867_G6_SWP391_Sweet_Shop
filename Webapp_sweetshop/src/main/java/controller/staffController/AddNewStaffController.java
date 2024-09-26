package controller.staffController;

import dal.staff.StaffProcess;
import model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import until.UploadFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AddNewStaffController", value = {"/addstaff"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddNewStaffController extends HttpServlet {

    private StaffProcess staffProcess;

    @Override
    public void init() {
        staffProcess = new StaffProcess(); // Load danh sách staff ban đầu
    }

    //xác định thư mục nơi sẽ lưu trữ ảnh đại diện
    private static final String UPLOAD_DIRECTORY = "assets/image/avatar";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/admin/add_new_staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // upload file
            UploadFile uploadFile = new UploadFile();
            List<String> imgProduct = uploadFile.fileUpload(request, response);
            // Process normal fields
            String username = request.getParameter("uname").trim();
            String password = request.getParameter("pass").trim();
            String repeatPassword = request.getParameter("rpass").trim();
            String fullName = request.getParameter("fullname").trim();
            String genderParam = request.getParameter("gender").trim();
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("mobno").trim();
            String dobParam = request.getParameter("dob").trim();
            String address = request.getParameter("address").trim();
            String statusParam = request.getParameter("status").trim();

            // Check password
            if (!password.equals(repeatPassword)) {
                request.setAttribute("message", "Passwords do not match.");
                request.getRequestDispatcher("/page/admin/add_new_staff.jsp").forward(request, response);
                return;
            }

            // Process file upload (image file)
            Part filePart = request.getPart("profilePic");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            //Đảm bảo rằng tệp tải lên thực sự được nhận bởi servlet
            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("message", "No file uploaded.");
                request.getRequestDispatcher("/page/admin/add_new_staff.jsp").forward(request, response);
                return;
            }

            // Check file extension
            String fileExtension = getFileExtension(fileName);
            if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("jpeg")) {
                request.setAttribute("message", "File must be a JPG image.");
                request.getRequestDispatcher("/page/admin/add_new_staff.jsp").forward(request, response);
                return;
            }

            // Lấy đường dẫn thực tế đến thư mục gốc của ứng dụng
            String realPath = getServletContext().getRealPath("");

            // Kết hợp đường dẫn gốc với đường dẫn thư mục tải lên
            String uploadPath = realPath + File.separator + UPLOAD_DIRECTORY;

            // Tạo thư mục nếu nó không tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lưu tệp tải lên vào thư mục đã xác định
            filePart.write(uploadPath + File.separator + fileName);

            // Tiếp tục xử lý các giá trị khác và thêm nhân viên vào cơ sở dữ liệu...
            boolean gender = "Male".equalsIgnoreCase(genderParam);
            int status = "Active".equalsIgnoreCase(statusParam) ? 1 : 0;
            Date dob = Date.valueOf(dobParam);

            // Tạo một đối tượng Staff mới và thiết lập các thuộc tính của nó
            Staff newStaff = new Staff();
            newStaff.setUsername(username);
            newStaff.setPassword(password);
            newStaff.setFullName(fullName);
            newStaff.setGender(gender);
            newStaff.setEmail(email);
            newStaff.setPhone(phone);
            newStaff.setDob(dob);
            newStaff.setAddress(address);
            newStaff.setStatus(status);
            newStaff.setRole(2); // Role for staff is 2
            newStaff.setAvatar(UPLOAD_DIRECTORY + "/" + fileName);

            // Thiết lập thời gian hiện tại cho createdAt và updatedAt

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            newStaff.setCreatedAt(currentDate);
            newStaff.setUpdatedAt(currentDate);

            // thêm nhân viên vào cơ sở dữ liệu
            boolean addSuccess = staffProcess.add(newStaff);

            if (addSuccess) {
                request.setAttribute("message", "Staff added successfully!");
            } else {
                request.setAttribute("message", "Failed to add staff.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "An error occurred: " + ex.getMessage());
        }

        // Forward to the add_new_staff.jsp page with a message
        request.getRequestDispatcher("/page/admin/add_new_staff.jsp").forward(request, response);
    }

    // Method to get file extension
    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0 && lastIndexOfDot < fileName.length() - 1) {
            return fileName.substring(lastIndexOfDot + 1);
        }
        return "";
    }
}
