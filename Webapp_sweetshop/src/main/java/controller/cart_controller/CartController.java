//package controller.cart_controller;
//
//import dal.cart.CartDao;
//import dal.order.OrderProcess;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
//import jakarta.servlet.annotation.WebServlet;
//import model.CartDetail;
//import model.Order;
//
//@WebServlet(value = "/cartcontroller")
//public class CartController extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    private transient CartDao cartDao;
//
//    @Override
//    public void init() {
//        cartDao = new CartDao();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response){
//        String action = request.getParameter("action");
//        int userId = 1;
//
//        if (action == null) {
//            action = "";
//        }
//
//        try {
//            switch (action) {
//                case "remove":
//                    int productDetailID = Integer.parseInt(request.getParameter("productDetailID"));
//                    removeCartItem(response, userId, productDetailID);
//                    break;
//                case "checkout":
//                    checkout(request, response, userId);
//                    break;
//                case "confirm":
//                    confirmOrder(request, response, userId);
//                    break;
//                default:
//                    showCart(request, response, userId);
//                    break;
//            }
//        } catch (NumberFormatException ex) {
//            handleError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid product detail ID.");
//        } catch (Exception ex) {
//            handleError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        doGet(request, response);
//    }
//    static Logger logger = Logger.getLogger(CartController.class.getName());
//
//    private void handleError(HttpServletResponse response, int statusCode, String message) {
//        try {
//            logger.info(message);
//            response.sendError(statusCode, message);
//        } catch (IOException e) {
//            logger.warning("Failed to send error response: " + e.getMessage());
//        }
//    }
//
//    private void showCart(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
//        List<CartDetail> cartItems = cartDao.getAllCartItems(userId);
//        request.setAttribute("cartItems", cartItems);
//        request.getRequestDispatcher("view/cart.jsp").forward(request, response);
//    }
//
//    private void removeCartItem(HttpServletResponse response, int userId, int productDetailID) throws IOException {
//        cartDao.removeCartItem(productDetailID, userId);
//        response.sendRedirect("cartcontroller");
//    }
//
//    private static String selectedCartItemsString = "selectedCartItems";
//    private void checkout(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
//        String[] selectedItems = request.getParameterValues("selectedItems");
//
//        if (selectedItems == null || selectedItems.length == 0) {
//            request.setAttribute("errorMessage", "You have not selected a product yet!");
//            showCart(request, response, userId);
//            return;
//        }
//
//        List<CartDetail> selectedCartItems = new ArrayList<>();
//        for (String itemId : selectedItems) {
//            int productDetailID = Integer.parseInt(itemId);
//            CartDetail cartItem = cartDao.getCartItemByProductDetailID(userId, productDetailID);
//            if (cartItem != null) {
//                selectedCartItems.add(cartItem);
//            }
//        }
//
//        request.setAttribute(selectedCartItemsString, selectedCartItems);
//        request.getRequestDispatcher("view/cart-contact.jsp").forward(request, response);
//    }
//
//    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//
//
//        String errorMessage = validateOrderData(name, email, phone);
//        if (errorMessage != null) {
//            request.setAttribute("errorMessage", errorMessage);
//            request.setAttribute(selectedCartItemsString, request.getAttribute("selectedCartItems"));
//            request.getRequestDispatcher("view/cart-contact.jsp").forward(request, response);
//            return;
//        }
//
//        Order order = new Order(name, phone, email, address, userId);
//        OrderProcess orderDao = new OrderProcess();
//        orderDao.create(order);
//
//        request.setAttribute("order", order);
//        request.getRequestDispatcher("view/cart-completion.jsp").forward(request, response);
//    }
//
//
//    private String validateOrderData(String name, String email, String phone) {
//
//        if (name == null || name.trim().isEmpty() || !name.matches("[A-Za-z\\s]+")) {
//            return "Name must not contain numbers or special characters.";
//        }
//
//        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
//            return "Invalid email format. Please enter a valid email.";
//        }
//
//        if (phone == null || !phone.matches("\\d{10,11}")) {
//            return "Phone number must be 10 or 11 digits and contain only numbers.";
//        }
//        return null;
//    }
//}
//
//
//
//
