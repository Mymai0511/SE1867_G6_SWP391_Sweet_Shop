package until;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author trantoan
 */
public class UploadFile {

    //lưu đường dẫn tới thư mục mà các tệp được tải lên sẽ được lưu trữ
    private String UPLOAD_DIRECTORY;


    //xác định vị trí lưu tệp, kiểm tra và lưu các tệp đã được tải lên
    public List<String> fileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the real path to the "build/web" directory
        String realPath = request.getServletContext().getRealPath("");
        // Navigate to the "web/uploadFiles" directory
        Path uploadPath = Paths.get(realPath).getParent().getParent().resolve("webapp/assets/image/avatar");

        //Lưu trữ đường dẫn dưới dạng string
        UPLOAD_DIRECTORY = uploadPath.toString();

        //Tạo thư mục nếu không tồn tại
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //danh sách để lưu tên của các tệp đã tải lên
        List<String> uploadedFileNames = new ArrayList<>();

        try {
            // Vòng lặp để xử lý từng file
            for (Part part : request.getParts()) {
                // Kiểm tra và lưu file
                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                    String fileName = extractFileName(part);// Lấy tên của file sau khi được xử lý tính duy nhất
                    if (fileName != null && !fileName.isEmpty()) {
                        // Lưu tập tin vào thư mục đã chỉ định
                         // File.separator: "/" cung cấp dấu phân cách đường dẫn hệ thống phù hợp với hệ điều hành
                        part.write(UPLOAD_DIRECTORY + File.separator + fileName);
                        // Thêm tên file vào danh sách các file đã được lưu
                        uploadedFileNames.add(fileName);
                    }
                }
            }
        } catch (ServletException | IOException e) {
            System.out.println("===========================");
            System.out.println("Error at upload file: " + e.getMessage());
            e.printStackTrace(); // Changed from e.getStackTrace() to e.printStackTrace() for proper error logging
        }
        return uploadedFileNames;
    }

    //xử lý các tên file để đảm bảo tính duy nhất
    private String extractFileName(Part part) {
        //- lấy tiêu đề "content-disposition": chứa thông tin về loại nội dung, tên tệp...
        //- "content-disposition" thường có nhiều phần được phân tách bằng dấu chấm phẩy (;).
        //- dựa vào (;) tách các phần này thành một mảng chuỗi (items).
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {//Tìm Tên Tệp
                // Xử lại bỏ (= và ") để lấy tên file ban đầu
                String originalFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                // Tạo một chuỗi duy nhất (UUID) để thêm vào tên tập tin
                String uniqueID = UUID.randomUUID().toString();

                // Tách tên tệp và phần mở rộng của nó
                String fileExtension = "";// phần mo rộng sau dấu "."
                int dotIndex = originalFileName.lastIndexOf('.');//vị trí dấu "."
                if (dotIndex != -1) {
                    fileExtension = originalFileName.substring(dotIndex); //phần mở rộng
                    originalFileName = originalFileName.substring(0, dotIndex);// tên file
                }

                // Kết hợp tên tập tin ban đầu với UUID để tạo ra tên tập tin duy nhất
                return originalFileName + "_" + uniqueID + fileExtension;
            }
        }
        return "";
    }
}