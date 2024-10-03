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
     * Lấy tổng số sản phẩm từ bảng product theo điều kiện tìm kiếm
     */
    public int getTotalProducts(String searchName) {
        StringBuilder sql = new StringBuilder("" +
                "SELECT COUNT(DISTINCT p.id) AS total " +
                "FROM product p " +
                "JOIN productDetail pd ON p.id = pd.productID " +
                "WHERE p.status = ? ");
        // Nếu có điều kiện tìm kiếm
        if (searchName != null && !searchName.isEmpty()) {
            sql.append("AND p.name LIKE ?");
        }
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql.toString());
            ps.setString(1, "1");
            // Thiết lập tham số tìm kiếm
            if (searchName != null && !searchName.isEmpty()) {
                ps.setString(2, "%" + searchName + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return 0;
    }

    /**
     * Lấy danh sách sản phẩm cho một trang cụ thể, sắp xếp theo giá trị min của giá và tìm kiếm theo tên
     */
    public List<Product> getProductsByPage(String searchName, int limit, int offset, String sortByMinPriceAsc) {
        List<Product> productList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT p.*, MIN(pd.price) AS minPrice FROM product p "
                + "JOIN productDetail pd ON p.id = pd.productID WHERE p.status = ? ");
        // Nếu có tìm kiếm theo tên
        if (searchName != null && !searchName.isEmpty()) {
            query.append("and p.name LIKE ? ");
        }
        // Nhóm theo productID để lấy giá trị min
        query.append("GROUP BY p.id ");
        // Thêm điều kiện sắp xếp theo giá trị min
        query.append("ORDER BY minPrice ");
        query.append(sortByMinPriceAsc);
        query.append(" LIMIT ? OFFSET ?");
        try {
            PreparedStatement ps = this.connection.prepareStatement(query.toString());
            ps.setString(1, "1");
            int paramIndex = 2;
            // Thiết lập tham số tìm kiếm tên
            if (searchName != null && !searchName.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchName + "%");
            }
            ps.setInt(paramIndex++, limit);
            ps.setInt(paramIndex, offset);
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
