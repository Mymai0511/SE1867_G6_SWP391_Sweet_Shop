package until;

import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;

/**
 *
 * @author
 */
public class UploadFile {
    private static final long serialVersionUID = 1L;
    private static final long MAX_TOTAL_SIZE = 10 * 1024 * 1024; // 10MB

    /**
     * process all file after upload
     *
     * @param fileParts file user upload from local
     * @param imageFile name tag input
     * @return String image after convert with format '...;...'
     * @throws IOException exception error
     */
    public static String processFileParts(Collection<Part> fileParts, String imageFile) throws IOException {
        long totalSize = 0;
        StringBuilder base64Images = new StringBuilder();
        for (Part filePart : fileParts) {
            if (filePart.getName().equals(imageFile)) {
                String contentType = filePart.getContentType();
                try (InputStream inputStream = filePart.getInputStream()) {
                    // Gọi phương thức convertImageToBase64 với các tham số mới
                    String base64Image = convertImageToBase64(inputStream, contentType, totalSize);
                    base64Images.append(base64Image).append(";");
                    // Cập nhật tổng kích thước
                    totalSize += filePart.getSize();
                }
            }
        }
        return base64Images.toString();
    }

    /**
     * convert image to string using base64
     *
     * @param inputStream file image
     * @param contentType type file input
     * @param totalSize total size file get from local server
     * @return String after convert from image
     * @throws IOException
     */
     public static String convertImageToBase64(InputStream inputStream, String contentType, long totalSize) throws IOException {
        // Kiểm tra nếu contentType không phải là ảnh
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("File is not an image.");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        long currentSize = 0;
        // Đọc dữ liệu từ inputStream và ghi vào outputStream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            currentSize += bytesRead;
            if (currentSize + totalSize > MAX_TOTAL_SIZE) {
                throw new IOException("Total file size exceeds 10MB.");
            }
            outputStream.write(buffer, 0, bytesRead);
        }
        // Chuyển đổi mảng byte thành chuỗi Base64
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}