package dal.cart;

/**
 *
 * @author hoang
 */

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.CartDetail;

public class CartDao extends DBContext {

    public List<CartDetail> getAllCartItems() {
        String sql = "select * from `cart`";
        List<CartDetail> cartList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            System.out.println("No cart!");
        }
        CartDetail item = new CartDetail();
        cartList.add(item);
        return cartList;
    }

    public double calculateSubtotal() {
       double subtotal = 0;
       
       return subtotal;
    }

    public double getDiscount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void removeCartItem(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateCartItemQuantity(int productId, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
