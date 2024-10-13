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
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Base64;
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
        staffProcess = new StaffProcess();
    }

    //xác định thư mục nơi sẽ lưu trữ ảnh đại diện
    private static final String UPLOAD_DIRECTORY = "assets/avatar";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/admin/add-new-staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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

            // Lưu các giá trị đã nhập lại trong request attributes để gửi về trang JSP nếu gặp lỗi
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("repeatPassword", repeatPassword);
            request.setAttribute("myName", fullName);
            request.setAttribute("genderParam", genderParam);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("dobParam", dobParam);
            request.setAttribute("address", address);
            request.setAttribute("statusParam", statusParam);

            // Check password
            if (!password.equals(repeatPassword)) {
                request.setAttribute("message", "Passwords do not match.");
                request.getRequestDispatcher("/page/admin/add-new-staff.jsp").forward(request, response);
                return;
            }

            // Check file avatar
            // phần tệp từ request HTTP với tên "profilePic"
            Part filePart = request.getPart("profilePic");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            //file có bị null hay không or kích thước bằng 0
            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("message", "No file uploaded.");
                request.getRequestDispatcher("/page/admin/add-new-staff.jsp").forward(request, response);
                return;
            }

            // kiểm tra định dạng tệp
            String fileExtension = getFileExtension(fileName);
            if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("jpeg")) {
                request.setAttribute("message", "File must be a JPG image.");
                request.getRequestDispatcher("/page/admin/add-new-staff.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi ảnh sang Base64
            String base64Image;
            try (InputStream inputStream = filePart.getInputStream()) {
                byte[] imageBytes = new byte[(int) filePart.getSize()];
                inputStream.read(imageBytes);
                base64Image = Base64.getEncoder().encodeToString(imageBytes);
            }

//            // upload file dùng class UploadFile cho quá trình tải file lên.
//            UploadFile uploadFile = new UploadFile();//Lấy phần tệp từ yêu cầu
//            List<String> imgStaff = uploadFile.fileUpload(request, response);//Lấy tên tệp

            // check các giá trị khác và thêm nhân viên vào cơ sở dữ liệu
            boolean gender = "Male".equalsIgnoreCase(genderParam);
            int status = "Active".equalsIgnoreCase(statusParam) ? 1 : 0;
            Date dob = Date.valueOf(dobParam);

            // Tạo một đối tượng Staff mới và thiết lập các thuộc tính của nó
            Staff newStaff = new Staff();
            newStaff.setAvatar(base64Image

            );
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

//            newStaff.setAvatar(imgStaff.get(0));// tải 1 ảnh nen lay image dau tien

            // Thiết lập thời gian hiện tại cho createdAt và updatedAt
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            newStaff.setCreatedAt(currentDate);
            newStaff.setUpdatedAt(currentDate);

            // thêm nhân viên vào cơ sở dữ liệu
            boolean addSuccess = staffProcess.add(newStaff);

            if (addSuccess) {
                // Nếu thêm thành công, xóa các thuộc tính đã thiết lập
                request.removeAttribute("username");
                request.removeAttribute("password");
                request.removeAttribute("repeatPassword");
                request.removeAttribute("myName");
                request.removeAttribute("genderParam");
                request.removeAttribute("email");
                request.removeAttribute("phone");
                request.removeAttribute("dobParam");
                request.removeAttribute("address");
                request.removeAttribute("statusParam");
                request.setAttribute("message", "Staff added successfully!");
            } else {
                request.setAttribute("message", "Failed to add staff.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "An error occurred: " + ex.getMessage());
        }
        request.getRequestDispatcher("/page/admin/add-new-staff.jsp").forward(request, response);
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
