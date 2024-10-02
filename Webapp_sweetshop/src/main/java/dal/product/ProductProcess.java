package dal.product;

import dal.DAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProductProcess extends DAO {
    public static ProductProcess INSTANCE = new ProductProcess();

    private ProductProcess() {
    }

    /**
     * get all product from database
     *
     * @return list product
     */
    public List<Product> read() {
        String sql = "select * from `product`";
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setIngredient(rs.getString("ingredient"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getInt("status"));
                product.setCreatedAt(rs.getDate("createdAt"));
                product.setUpdatedAt(rs.getDate("updatedAt"));
                product.setCategoryID(rs.getInt("categoryID"));
                productList.add(product);
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return productList;
    }

    /**
     * Lấy tổng số sản phẩm từ bảng product
     */
    public int getTotalProducts() {
        String sql = "SELECT COUNT(*) AS total FROM product";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return 0;
    }

    /**
     * Lấy danh sách sản phẩm cho một trang cụ thể
     */
    public List<Product> getProductsByPage(int limit, int offset) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM product LIMIT ? OFFSET ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setIngredient(rs.getString("ingredient"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getInt("status"));
                product.setCreatedAt(rs.getDate("createdAt"));
                product.setUpdatedAt(rs.getDate("updatedAt"));
                product.setCategoryID(rs.getInt("categoryID"));
                productList.add(product);
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return productList;
    }

    /**
     * get product by id product
     *
     * @param id id product
     * @return object product or null if not found
     */
    public Product getProductById(String id) {
        String sql = "select * from `product` where id = ?";
        Product product = null;
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(Integer.parseInt(rs.getString("id")));
                product.setName(rs.getString("name"));
                product.setIngredient(rs.getString("ingredient"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getInt("status"));
                product.setCreatedAt(rs.getDate("createdAt"));
                product.setUpdatedAt(rs.getDate("updatedAt"));
                product.setCategoryID(rs.getInt("categoryID"));
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return product;
    }

    public static void main(String[] args) {
        System.out.println(ProductProcess.INSTANCE.getProductById("1"));
    }
}
