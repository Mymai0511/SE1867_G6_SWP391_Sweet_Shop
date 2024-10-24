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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "update_product", value = "/update_product")
public class UpdateProduct extends HttpServlet {
    String pid = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            pid = request.getParameter("id");
            if(pid == null){
                throw new Exception();
            }
            Product product = ProductProcess.INSTANCE.getProductById(pid);
            List<ProductDetail> productDetailList = ProductDetailProcess.INSTANCE.getProductDetailByProductID(pid);
            List<Media> mediaList = MediaProcess.INSTANCE.getAllMediaByProductID(pid);
            request.setAttribute("product", product);
            request.setAttribute("productDetailList", productDetailList);
            request.setAttribute("mediaList", mediaList);
            List<Category> categoryList = CategoryProcess.INSTANCE.getCatagoryActive();
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/page/product/update_product.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/view_list_product?mess=Please choose a product&type=error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
