package controller.staffController;

import dal.category.CategoryProcess;
import dal.media.MediaProcess;
import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import session.SessionRepo;
import validation.CheckInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "list_product", value = "/view_list_product")
public class HomePage extends HttpServlet {
    // Số sản phẩm mỗi trang
    private static final int LIMIT = 10;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();
        int page = 1;
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
        String sort1 = request.getParameter("sort1") == null ? "asc" : request.getParameter("sort1");
        String search1 = request.getParameter("search1") == null ? "" : request.getParameter("search1");
        String findByName = SessionRepo.getSearch(request, response);
        if (findByName == null) {
            findByName = CheckInput.checkInputString(request.getParameter("search") == null ? "" : request.getParameter("search"));
            if (findByName != null) {
                SessionRepo.setSearch(request, response, findByName);
            }
        } else {
            String checkSearch = request.getParameter("search") == null ? search1 : request.getParameter("search");
            if (!findByName.equals(checkSearch)) {
                findByName = CheckInput.checkInputString(request.getParameter("search") == null ? "" : request.getParameter("search"));
                findByName = findByName == null ? "" : findByName;
                SessionRepo.setSearch(request, response, findByName);
            }
        }
        String sort = SessionRepo.getSort(request, response);
        if (sort == null) {
            sort = CheckInput.checkInputString(request.getParameter("sort")) == null
                    ? "asc"
                    : CheckInput.checkInputString(request.getParameter("sort"));
            SessionRepo.setSort(request, response, sort);
        } else {
            String checkSort = request.getParameter("sort") == null ? sort1 : request.getParameter("sort");
            if (!sort.equals(checkSort)) {
                sort = CheckInput.checkInputString(request.getParameter("sort")) == null
                        ? "asc"
                        : CheckInput.checkInputString(request.getParameter("sort"));
                SessionRepo.setSort(request, response, sort);
            }
        }

        int totalProducts = ProductProcess.INSTANCE.getFullTotalProducts(findByName);
        int totalPages = (int) Math.ceil((double) totalProducts / LIMIT);
        // Đảm bảo trang hiện tại không vượt quá tổng số trang
        if (page > totalPages) page = totalPages;
        // 2. Tính OFFSET
        int offset = (page - 1) * LIMIT;
        // 3. Lấy danh sách sản phẩm cho trang hiện tại
        List<Product> products = ProductProcess.INSTANCE.getFullProductsByPage(findByName, LIMIT, offset, sort);
        // 4. Thiết lập các thuộc tính để chuyển sang JSP
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("user", SessionRepo.getUser(request, response));
        request.setAttribute("productDetail", ProductDetailProcess.INSTANCE);
        request.setAttribute("category", CategoryProcess.INSTANCE);
        request.setAttribute("media", MediaProcess.INSTANCE);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("page/staff/home_page.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
