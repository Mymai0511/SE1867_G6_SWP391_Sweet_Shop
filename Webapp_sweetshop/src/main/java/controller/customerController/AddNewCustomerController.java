package controller.customerController;

import dal.Customer.CustomerProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Customer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.logging.Logger;

@WebServlet(name = "AddNewCustomerController", value = {"/addcustomer"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddNewCustomerController extends HttpServlet {

    private CustomerProcess customerProcess;
    private static final int ROLE_CUSTOMER = 1;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final Logger logger = Logger.getLogger(AddNewCustomerController.class.getName());

    @Override
    public void init() {
        customerProcess = new CustomerProcess();
    }

    private static final String UPLOAD_DIRECTORY = "assets/avatar";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Process normal fields
            String username = request.getParameter("uname").trim();
            String fullName = request.getParameter("myname").trim();
            String genderParam = request.getParameter("gender").trim();
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("mobno").trim();
            String dobParam = request.getParameter("dob").trim();
            String address = request.getParameter("address").trim();
            String statusParam = request.getParameter("status").trim();

            // Lưu các giá trị đã nhập lại trong request attributes để gửi về trang JSP nếu gặp lỗi
            request.setAttribute("username", username);
            request.setAttribute("myName", fullName);
            request.setAttribute("genderParam", genderParam);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("dobParam", dobParam);
            request.setAttribute("address", address);
            request.setAttribute("statusParam", statusParam);

            // Check file avatar
            Part filePart = request.getPart("profilePic");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Kiểm tra kích thước file
            if (filePart.getSize() == 0) {
                request.setAttribute("message", "No file uploaded.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            if (filePart.getSize() > MAX_FILE_SIZE) {
                request.setAttribute("message", "File size exceeds the maximum limit of 10MB.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Kiểm tra định dạng tệp
            String fileExtension = getFileExtension(fileName);
            if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("jpeg")) {
                request.setAttribute("message", "File must be a JPG image.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi ảnh sang Base64
            String base64Image = null;
            try (InputStream inputStream = filePart.getInputStream();
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                // Đọc dữ liệu từ inputStream và ghi vào outputStream
                byte[] buffer = new byte[1024]; // Sử dụng bộ đệm 1024 bytes
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead); // Ghi dữ liệu vào outputStream
                }

                // Chuyển đổi mảng byte sang Base64
                base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());

            } catch (IOException e) {
                request.setAttribute("message", "Error reading the image file.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }


            // Kiểm tra username, email và phone
            if (customerProcess.isUsernameTaken(username)) {
                request.setAttribute("message", "Username already taken.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            if (customerProcess.isEmailTaken(email)) {
                request.setAttribute("message", "Email already in use.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            if (customerProcess.isPhoneTaken(phone)) {
                request.setAttribute("message", "Phone number already in use.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Kiểm tra định dạng email
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("message", "Invalid email format.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Kiểm tra định dạng số điện thoại
            if (!phone.matches("^0\\d{9}$")) {
                request.setAttribute("message", "Invalid phone number format.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Kiểm tra ngày sinh
            LocalDate birthDate = null;
            try {
                birthDate = LocalDate.parse(dobParam);
            } catch (DateTimeParseException e) {
                request.setAttribute("message", "Invalid date format.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }
            LocalDate today = LocalDate.now();
            if (Period.between(birthDate, today).getYears() < 0) {
                request.setAttribute("message", "Staff must be at least 0 years old.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            if (username.isEmpty() || fullName.isEmpty() || genderParam.isEmpty() ||
                    email.isEmpty() || phone.isEmpty() || dobParam.isEmpty() || address.isEmpty()) {
                request.setAttribute("message", "All fields are required.");
                request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi giới tính và trạng thái
            boolean gender = "Male".equalsIgnoreCase(genderParam);
            int status = "Active".equalsIgnoreCase(statusParam) ? 1 : 0;

            // Tạo đối tượng Customer mới
            Customer newCustomer = new Customer();
            newCustomer.setAvatar(base64Image);
            newCustomer.setUsername(username);
            newCustomer.setPassword("null");
            newCustomer.setFullName(fullName);
            newCustomer.setGender(gender);
            newCustomer.setEmail(email);
            newCustomer.setPhone(phone);
            newCustomer.setDob(Date.valueOf(birthDate));
            newCustomer.setAddress(address);
            newCustomer.setStatus(status);
            newCustomer.setRole(ROLE_CUSTOMER);

            Date currentDate = new Date(System.currentTimeMillis());
            newCustomer.setCreatedAt(currentDate);
            newCustomer.setUpdatedAt(currentDate);

            // Thêm Staff vào cơ sở dữ liệu
            boolean addSuccess = customerProcess.add(newCustomer);

            if (addSuccess) {
                request.setAttribute("message", "Customer added successfully!");
            } else {
                request.setAttribute("message", "Failed to add customer.");
            }

        } catch (Exception ex) {
            logger.severe("Error in AddNewCustomerController: " + ex.getMessage());
            request.setAttribute("message", "An unexpected error occurred. Please try again.");
        }
        request.getRequestDispatcher("/page/staff/add-new-customer.jsp").forward(request, response);
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0 && lastIndexOfDot < fileName.length() - 1) {
            return fileName.substring(lastIndexOfDot + 1);
        }
        return "";
    }
}