package controller.staffController;

import dal.category.CategoryProcess;
import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "add_new_product", value = "/add_new_product")
public class AddNewProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = CategoryProcess.INSTANCE.getCatagoryActive();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/page/staff/add-new-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productCategory = request.getParameter("productCategory");
        String[] productPrice = request.getParameterValues("productPrice");
        String[] productSize = request.getParameterValues("productSize");
        String productIngredient = request.getParameter("productIngredient");
        String productDescription = request.getParameter("productDescription");
        String productStatus = request.getParameter("productStatus");

        Product product = ProductProcess.INSTANCE.getProductByName(productName);
        if (product == null) {
            String productID = ProductProcess.INSTANCE.create(productName, productIngredient, productDescription, productStatus, productCategory);
            if (productID != null) {
                ProductDetailProcess.INSTANCE.create(productPrice, productSize, productID);
                response.sendRedirect( request.getContextPath() + "/view_list_product?mess=Insert new product successfully!&type=success");
            } else {
                request.setAttribute("mess", "Insert new product failed!");
                request.setAttribute("type", "error");
                request.setAttribute("productName", productName);
                request.setAttribute("productCategory", productCategory);
                request.setAttribute("productPrice", productPrice);
                request.setAttribute("productSize", productSize);
                request.setAttribute("productIngredient", productIngredient);
                request.setAttribute("productDescription", productDescription);
                request.setAttribute("productStatus", productStatus);
                doGet(request, response);
            }
        } else {
            request.setAttribute("mess", "Insert new product failed!");
            request.setAttribute("type", "error");
            request.setAttribute("productName", productName);
            request.setAttribute("productCategory", productCategory);
            request.setAttribute("productPrice", productPrice);
            request.setAttribute("productSize", productSize);
            request.setAttribute("productIngredient", productIngredient);
            request.setAttribute("productDescription", productDescription);
            request.setAttribute("productStatus", productStatus);
            doGet(request, response);
        }
    }
}
