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
import model.*;
import until.Unique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "viewDetail", value = "/viewdetail")
public class ProductDetailController extends HttpServlet {
    String idProduct = null;
    Product product = null;
    Category category = null;
    User user = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idProduct = request.getParameter("id");
        if (idProduct == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            product = ProductProcess.INSTANCE.getProductById(idProduct);
            category = CategoryProcess.INSTANCE.getCategoryByID(product.getCategoryID() + "");
            List<ProductDetail> productDetailList = ProductDetailProcess.INSTANCE.getProductDetailByProductID(idProduct);
            List<Media> mediaList = MediaProcess.INSTANCE.getAllMediaByProductID(idProduct);
            user = (User) request.getSession().getAttribute("user") == null
                    ? null
                    : (User) request.getSession().getAttribute("user");

            request.setAttribute("user", user);
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

        System.out.println(idProduct);
        System.out.println(product.toString());
        System.out.println(category.toString());
        String pd = request.getParameter("btnSize");
    }

    private List<String> uniqueSize(List<ProductDetail> list) {
        List<String> stringList = new ArrayList<>();
        for (ProductDetail productDetail : list) {
            stringList.add(productDetail.getSize() + "");
        }
        return Unique.uniqueStringsIgnoreCase(stringList);
    }
}
