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

        try {
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "delete":
                    deleteCartItem(request, response, userId);
                    break;
                case "update":
                    updateCartItem(request, response, userId);
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

        request.getRequestDispatcher("page/cakeMain/ShoppingCart.jsp").forward(request, response);
    }

    /**
     * delete product in a cart
     */
    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response, int cartid) throws IOException {
        int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
        cartDao.removeCartItem(productDetailId, cartid);
        response.sendRedirect("cartcontroller");
    }

    /**
     * delete change quantity of the product inside the cart
     */
    private void updateCartItem(HttpServletRequest request, HttpServletResponse response, int cartid) throws IOException {
        int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartDao.updateCartItemQuantity(productDetailId, quantity, cartid);
        response.sendRedirect("cartcontroller");
    }

    /**
     * Send to check out page
     */
    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("page/cakeMain/Checkout.jsp").forward(request, response);
    }
}

