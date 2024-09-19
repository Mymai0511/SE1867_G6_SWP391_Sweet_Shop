package controller.productController;

import dal.media.MediaProcess;
import dal.productDetail.ProductDetailProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Media;
import model.Product;
import model.ProductDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "viewDetail", value = "/viewdetail")
public class ProductDetailController extends HttpServlet {

    private String idProduct = null;
    private List<ProductDetail> productDetailList = new ArrayList<ProductDetail>();
    private List<Media> mediaList = new ArrayList<>();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idProduct = request.getParameter("id");
        if (idProduct == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            productDetailList = ProductDetailProcess.INSTANCE.getProductDetailByProductID(idProduct);
            mediaList = MediaProcess.INSTANCE.getAllMediaByProductID(idProduct);
            request.setAttribute("mediaList", mediaList);
            request.setAttribute("productDetail", ProductDetailProcess.INSTANCE);
            request.setAttribute("productDetailList", productDetailList);
            request.getRequestDispatcher("page/cakeMain/ShopDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
