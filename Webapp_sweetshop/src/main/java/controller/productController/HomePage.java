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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends HttpServlet {
    private List<Product> productList = new ArrayList<>();

    @Override
    public void init() {
        productList = ProductProcess.INSTANCE.read();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
