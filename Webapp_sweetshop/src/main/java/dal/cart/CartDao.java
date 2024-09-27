package dal.cart;

/**
 *
 * @author hoang
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.DBContext;
import model.CartDetail;
import model.Product;
import model.ProductDetail;

public class CartDao extends DBContext {

    /**
     * Get products in a shopping cart
     *
     * @return a product list in a shopping cart
     */
    public List<CartDetail> getAllCartItems(int userID) {
        String sql = "SELECT p.name AS product_name, pd.size, cd.quantity, pd.price\n" +
                "FROM cartDetail cd\n" +
                "JOIN productDetail pd ON cd.productDetailID = pd.id\n" +
                "JOIN product p ON pd.productID = p.id\n" +
                "JOIN cart c ON cd.cartID = c.id\n" +
                "WHERE c.userID = ?;";
        List<CartDetail> cartList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("product_name"));

                ProductDetail productDetail = new ProductDetail();
                productDetail.setSize(rs.getString("size"));
                productDetail.setPrice(rs.getFloat("price"));

                CartDetail item = new CartDetail();
                item.setQuantity(rs.getInt("quantity"));
                item.setProduct(product);
                item.setProductDetail(productDetail);

                cartList.add(item);
            }
        } catch (SQLException e) {
            System.out.println("No cart!");
        }
        return cartList;
    }

    public static void main(String[] args) {
        CartDao cartDao = new CartDao();

        List<CartDetail> cartItems = cartDao.getAllCartItems(1);

        for (CartDetail cd : cartItems) {
            System.out.println("Product Name: " + cd.getProduct().getName());
            System.out.println("Size: " + cd.getProductDetail().getSize());
            System.out.println("Price: " + cd.getProductDetail().getPrice());
            System.out.println("Quantity: " + cd.getQuantity());
            System.out.println("-----------------------------------");
        }
    }

    public double calculateSubtotal(int userId) {
        double subtotal = 0;
        String query = "SELECT SUM(pd.price * cd.quantity) AS subtotal "
                + "FROM cartdetail cd "
                + "JOIN cart c ON cd.cartID = c.id "
                + "JOIN productdetail pd ON cd.productDetailID = pd.id "
                + "WHERE c.userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subtotal = rs.getDouble("subtotal");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subtotal;
    }

    public double getDiscount(int userId) {

        return 0;
    }

    public void removeCartItem(int productDetailID, int userId) {
        String query = "DELETE cd FROM cartdetail cd "
                + "JOIN cart c ON cd.cartID = c.id "
                + "WHERE cd.productDetailID = ? AND c.userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productDetailID);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCartItemQuantity(int productDetailID, int quantity, int userId) {
        String query = "UPDATE cartdetail cd "
                + "JOIN cart c ON cd.cartID = c.id "
                + "SET cd.quantity = ? "
                + "WHERE cd.productDetailID = ? AND c.userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productDetailID);
            ps.setInt(3, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

