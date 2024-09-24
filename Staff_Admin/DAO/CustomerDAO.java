package Staff_Admin.DAO;

import DAO.DBContext;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    
    public class CustomerDAO {
    
        private final String jdbcURL = "jdbc:mysql://localhost:3306/shopcake"; // Replace with your DB details
        private final String jdbcUsername = "sa"; // Replace with your DB username
        private final String jdbcPassword = "12345"; // Replace with your DB password
    
        private static final String INSERT_CUSTOMER_SQL = "INSERT INTO user (username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM user WHERE id = ?";
        private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM user";
        private static final String DELETE_CUSTOMER_SQL = "DELETE FROM user WHERE id = ?";
        private static final String UPDATE_CUSTOMER_SQL = "UPDATE user SET username = ?, password = ?, fName = ?, gender = ?, email = ?, phone = ?, dob = ?, avatar = ?, address = ?, status = ?, createdAt = ?, updatedAt = ?, role = ? WHERE id = ?";
    
        public List<Customer> getAllCustomers() {
            List<Customer> customers = new ArrayList<>();
            // Code để lấy dữ liệu từ database
            try {
                String query = "SELECT * FROM customers";
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Customer customer = new Customer(
                        rs.getInt("id"), 
                        rs.getString("username"),
                        rs.getString("fullName"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("dob"),
                        rs.getString("avatar"),
                        rs.getString("address"),
                        rs.getString("status"),
                        rs.getString("role")
                    );
                    customers.add(customer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return customers;
        }
        