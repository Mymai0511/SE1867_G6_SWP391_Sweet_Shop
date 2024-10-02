package controller.productController;

import dal.category.CategoryProcess;
import dal.media.MediaProcess;
import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Media;
import model.Product;
import session.SessionRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends HttpServlet {


    // Số sản phẩm mỗi trang
    private static final int LIMIT = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();

        int page = 1; // Trang mặc định
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // 1. Tính tổng số sản phẩm
        int totalProducts = ProductProcess.INSTANCE.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / LIMIT);

        // Đảm bảo trang hiện tại không vượt quá tổng số trang
        if (page > totalPages) page = totalPages;

        // 2. Tính OFFSET
        int offset = (page - 1) * LIMIT;

        // 3. Lấy danh sách sản phẩm cho trang hiện tại
        List<Product> products = ProductProcess.INSTANCE.getProductsByPage(LIMIT, offset);
        // 4. Thiết lập các thuộc tính để chuyển sang JSP
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("user", SessionRepo.getUser(request,response));
        request.setAttribute("productDetail", ProductDetailProcess.INSTANCE);
        request.setAttribute("category", CategoryProcess.INSTANCE);
        request.setAttribute("media", MediaProcess.INSTANCE);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("view/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
