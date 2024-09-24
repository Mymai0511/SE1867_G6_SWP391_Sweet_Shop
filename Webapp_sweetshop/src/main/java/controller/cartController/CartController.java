package controller.cartController;

/**
 *
 * @author hoang
 */
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

        try {
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "delete":
                    deleteCartItem(request, response);
                    break;
                case "update":
                    updateCartItem(request, response);
                    break;
                case "checkout":
                    checkout(request, response);
                    break;
                default:
                    showCart(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartDetail> cartItems = cartDao.getAllCartItems();
        double subtotal = cartDao.calculateSubtotal();
        double discount = cartDao.getDiscount();
        double total = subtotal - discount;

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("discount", discount);
        request.setAttribute("total", total);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ShoppingCart.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        cartDao.removeCartItem(productId);
        response.sendRedirect("CartController");
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartDao.updateCartItemQuantity(productId, quantity);
        response.sendRedirect("CartController");
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.sendRedirect("bill.jsp");
    }

}
