package dal.product;

import dal.DAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProductProcess extends DAO {
    public static ProductProcess INSTANCE = new ProductProcess();

    private ProductProcess() {}

    private final List<Product> productList = new ArrayList<>();

    /**
     * get all product from database
     *
     * @return list product
     */
    public List<Product> read() {
        String sql = "select * from `product`";
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

    public static void main(String[] args) {
        for (Product product : ProductProcess.INSTANCE.read()) {
            System.out.println(product.getCategoryID());
        }
    }
}





