package dal.productDetail;

import dal.DAO;
import model.ProductDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailProcess extends DAO {
    public static ProductDetailProcess INSTANCE = new ProductDetailProcess();

    private ProductDetailProcess() {};

    private final List<ProductDetail> productDetail = new ArrayList<>();

    /**
     * get max value of price of a product
     *
     * @param productId id product
     * @return max value of price
     */
    public float getMaxPriceByProductId(String productId) {
        float maxPrice = 0;
        String sql = "SELECT MAX(price) FROM [productDetail] WHERE productID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maxPrice = rs.getFloat(1);
            }
        }catch (SQLException e) {
            status = e.getMessage();
        }
        return maxPrice;
    }

    /**
     * get min value of price of a product
     *
     * @param productId id product
     * @return min value of price
     */
    public float getMinPriceByProductId(String productId) {
        float minPrice = 0;
        String sql = "SELECT MIN(price) FROM [productDetail] WHERE productID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                minPrice = rs.getFloat(1);
            }
        }catch (SQLException e) {
            status = e.getMessage();
        }
        return minPrice;
    }

    public static void main(String[] args) {
        System.out.println(ProductDetailProcess.INSTANCE.getMaxPriceByProductId("1") + ", " + ProductDetailProcess.INSTANCE.getMinPriceByProductId("1"));
    }
}
