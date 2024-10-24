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
import jakarta.servlet.http.Part;
import model.Category;
import model.Product;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static until.UploadFile.processFileParts;

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
        // get value at the form
        String productName = request.getParameter("productName");
        String productCategory = request.getParameter("productCategory");
        String[] productPrice = request.getParameterValues("productPrice");
        String[] productSize = request.getParameterValues("productSize");
        String productIngredient = request.getParameter("productIngredient");
        String productDescription = request.getParameter("productDescription");
        String productStatus = request.getParameter("productStatus");
        Collection<Part> fileParts = request.getParts();
        // check name product
        Product product = ProductProcess.INSTANCE.getProductByName(productName);
        if (product == null) {
            // check image
            String base64Images;
            try {
                base64Images = processFileParts(fileParts, "imageFile");

            } catch (IOException e) {
                setAttributeError(request, e.getMessage(), productName,
                        productCategory, productPrice, productSize,
                        productIngredient, productDescription, productStatus);
                doGet(request, response);
                return;
            }
            // insert product
            String productID = ProductProcess.INSTANCE.create(productName, productIngredient, productDescription, productStatus, productCategory);
            if (productID != null) {
                MediaProcess.INSTANCE.add(base64Images, null, productID);
                ProductDetailProcess.INSTANCE.create(productPrice, productSize, productID);
                response.sendRedirect( request.getContextPath() + "/view_list_product?mess=Insert new product successfully!&type=success");
            } else {
                setAttributeError(request, "Insert new product failed!", productName,
                        productCategory, productPrice, productSize,
                        productIngredient, productDescription, productStatus);
                doGet(request, response);
            }
        } else {
            setAttributeError(request, "Name product is exist!", productName,
                    productCategory, productPrice, productSize,
                    productIngredient, productDescription, productStatus);
            doGet(request, response);
        }
    }

    private void setAttributeError(HttpServletRequest request, String messError, String productName,
                                   String productCategory, String[] productPrice, String[] productSize,
                                   String productIngredient, String productDescription, String productStatus)
            throws IOException  {
        request.setAttribute("mess", messError);
        request.setAttribute("type", "error");
        request.setAttribute("productName", productName);
        request.setAttribute("productCategory", productCategory);
        request.setAttribute("productPrice", productPrice);
        request.setAttribute("productSize", productSize);
        request.setAttribute("loop", productSize.length - 1);
        request.setAttribute("productIngredient", productIngredient);
        request.setAttribute("productDescription", productDescription);
        request.setAttribute("productStatus", productStatus);
    }
}
