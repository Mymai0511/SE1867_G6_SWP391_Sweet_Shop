package controller.cartController;

import dal.cart.CartDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import model.CartDetail;


@WebServlet(value = "/cartcontroller")
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CartDao cartDao;

    public void init() {
        cartDao = new CartDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = 1; // Assuming userId = 1 for now

        //first time request no productDetailID
        //So check if it null set it to 0.
        //If It's null, it causes the error 500
        String productDetailIDParam = request.getParameter("productDetailID");
        int productDetailID = 0;
        if (productDetailIDParam != null) {
            try {
                productDetailID = Integer.parseInt(productDetailIDParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        try {
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "remove":
                    removeCartItem(request, response, userId,productDetailID);
                    break;
                case "increase":
                    increaseCartItem(request, response, userId,productDetailID);
                    break;
                case "decrease":
                    decreaseCartItem(request, response, userId,productDetailID);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                default:
                    showCart(request, response, userId);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * get cart-detail from data
     */
    private void showCart(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        List<CartDetail> cartItems = cartDao.getAllCartItems(userId);
        double subtotal = cartDao.calculateSubtotal(userId);
        double discount = cartDao.getDiscount(userId);
        double total = subtotal - discount;

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("discount", discount);
        request.setAttribute("total", total);

        request.getRequestDispatcher("view/cart.jsp").forward(request, response);
    }

    private void increaseCartItem(HttpServletRequest request, HttpServletResponse response, int userId, int productDetailID) throws IOException {
        CartDetail cartItem = cartDao.getCartItemByProductDetailID(userId, productDetailID);
        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() - 1;
            if (newQuantity > 0) {
                cartDao.updateCartItemQuantity(productDetailID, newQuantity, userId);
            } else {
                cartDao.removeCartItem(productDetailID, userId);
            }
        }
        response.sendRedirect("cartcontroller");
    }

    private void decreaseCartItem(HttpServletRequest request, HttpServletResponse response, int userId, int productDetailID) throws IOException {
        CartDetail cartItem = cartDao.getCartItemByProductDetailID(userId, productDetailID);
        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + 1;
            cartDao.updateCartItemQuantity(productDetailID, newQuantity, userId);
        }
        response.sendRedirect("cartcontroller");
    }



    private void removeCartItem(HttpServletRequest request, HttpServletResponse response, int userId, int productDetailID) throws IOException {
        cartDao.removeCartItem(productDetailID, userId);
        response.sendRedirect("cartcontroller");
    }



    /**
     * Send to check out page
     */
    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("page/cakeMain/Checkout.jsp").forward(request, response);
    }
}

