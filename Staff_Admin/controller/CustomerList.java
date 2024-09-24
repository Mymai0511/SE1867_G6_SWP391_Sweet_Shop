package Staff_Admin.controller;

import dal.category.CategoryProcess;
import dal.media.MediaProcess;
import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import model.Media;
import DAO.CustomerDAO;
import model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class CustomerList extends HttpServlet {
private List<Product> productList = new ArrayList<>();
    private List<Media> mediaList = new ArrayList<>();

    @Override
    public void init() {
        productList = ProductProcess.INSTANCE.read();
        mediaList = MediaProcess.INSTANCE.read();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("productDetail", ProductDetailProcess.INSTANCE);
        request.setAttribute("category", CategoryProcess.INSTANCE);
        request.setAttribute("media", MediaProcess.INSTANCE);
        request.setAttribute("productList", productList);
        request.setAttribute("mediaList", mediaList);
        request.getRequestDispatcher("page/cakeMain/Index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
public class CustomerList {
    private CustomerDAO customerDAO;

    public CustomerList() {
        customerDAO = new CustomerDAO();
    }

    public List<Customer> getCustomerList() {
        return customerDAO.getAllCustomers();
    }
}
