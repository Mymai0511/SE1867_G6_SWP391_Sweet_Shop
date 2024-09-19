package dal.productDetail;

import dal.DAO;
import model.ProductDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailProcess extends DAO {
    public static ProductDetailProcess INSTANCE = new ProductDetailProcess();

    private ProductDetailProcess() {};

    private final List<ProductDetail> productDetailList = new ArrayList<>();

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
        for (ProductDetail productDetail : ProductDetailProcess.INSTANCE.getProductDetailByProductID("1")) {
            System.out.println(productDetail.toString());
        }
    }

    /**
     * get all product detail by id product
     *
     * @param idProduct id product
     * @return list product detail
     */
    public List<ProductDetail> getProductDetailByProductID(String idProduct) {
        String sql = "SELECT * FROM [productDetail] WHERE productID = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setId(rs.getInt("id"));
                productDetail.setSize(rs.getString("size"));
                productDetail.setPrice(rs.getFloat("price"));
                productDetail.setProductID(rs.getInt("productID"));
                productDetailList.add(productDetail);
            }
        } catch (SQLException e) {
            this.status = e.getMessage();
        }
        return productDetailList;
    }
}
