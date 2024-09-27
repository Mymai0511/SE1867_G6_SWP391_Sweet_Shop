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
        int userId = 1; // This should be dynamically obtained from session or login

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

    private void showCart(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
        List<CartDetail> cartItems = cartDao.getAllCartItems(userId);
        double subtotal = cartDao.calculateSubtotal(userId);
        double discount = cartDao.getDiscount(userId);
        double total = subtotal - discount;

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("discount", discount);
        request.setAttribute("total", total);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ShoppingCart.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException {
        int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
        cartDao.removeCartItem(productDetailId, userId);
        response.sendRedirect("cartcontroller");
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException {
        int productDetailId = Integer.parseInt(request.getParameter("productDetailId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartDao.updateCartItemQuantity(productDetailId, quantity, userId);
        response.sendRedirect("cartcontroller");
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("bill.jsp");
    }
}

