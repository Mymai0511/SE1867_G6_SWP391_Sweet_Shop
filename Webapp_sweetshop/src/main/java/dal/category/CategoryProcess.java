package dal.category;

import dal.DAO;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryProcess extends DAO {
    public static CategoryProcess INSTANCE = new CategoryProcess();

    private CategoryProcess() {}

    private final List<Category> categories = new ArrayList<>();

    public Category getCategoryByID(String id) {
        Category category = new Category();
        String sql = "SELECT * FROM [category] WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));
            }
        }catch (SQLException e) {
            status = e.getMessage();
        }
        return category;
    }

    public static void main(String[] args) {
        System.out.println(CategoryProcess.INSTANCE.getCategoryByID("1").getName());
    }
}
