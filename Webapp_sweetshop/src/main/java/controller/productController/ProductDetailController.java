package controller.productController;

import dal.category.CategoryProcess;
import dal.media.MediaProcess;
import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Media;
import model.Product;
import model.ProductDetail;
import until.Unique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "viewDetail", value = "/viewdetail")
public class ProductDetailController extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduct = request.getParameter("id");
        if (idProduct == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            Product product = ProductProcess.INSTANCE.getProductById(idProduct);
            Category category = CategoryProcess.INSTANCE.getCategoryByID(product.getCategoryID() + "");
            List<ProductDetail> productDetailList = ProductDetailProcess.INSTANCE.getProductDetailByProductID(idProduct);
            List<Media> mediaList = MediaProcess.INSTANCE.getAllMediaByProductID(idProduct);

            request.setAttribute("size", uniqueSize(productDetailList));
            request.setAttribute("category", category);
            request.setAttribute("product", product);
            request.setAttribute("mediaList", mediaList);
            request.setAttribute("productDetail", ProductDetailProcess.INSTANCE);
            request.setAttribute("productDetailList", productDetailList);
            request.getRequestDispatcher("page/cakeMain/ShopDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<String> uniqueSize (List<ProductDetail> list) {
        List<String> stringList = new ArrayList<>();
        for (ProductDetail productDetail : list) {
            stringList.add(productDetail.getSize() + "");
        }
        return Unique.uniqueStringsIgnoreCase(stringList);
    }
}
