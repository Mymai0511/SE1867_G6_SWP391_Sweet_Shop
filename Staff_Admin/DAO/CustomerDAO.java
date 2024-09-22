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
    
        public CustomerDAO() {}
    
        // Establishing connection to the database
        protected Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }
    
        // Insert a new customer into the database
        public void insertCustomer(User customer) throws SQLException {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.setString(3, customer.getfName());
                preparedStatement.setBoolean(4, customer.isGender());
                preparedStatement.setString(5, customer.getEmail());
                preparedStatement.setString(6, customer.getPhone());
                preparedStatement.setDate(7, Date.valueOf(customer.getDob()));
                preparedStatement.setString(8, customer.getAvatar());
                preparedStatement.setString(9, customer.getAddress());
                preparedStatement.setInt(10, customer.getStatus());
                preparedStatement.setDate(11, Date.valueOf(customer.getCreatedAt()));
                preparedStatement.setDate(12, Date.valueOf(customer.getUpdatedAt()));
                preparedStatement.setInt(13, customer.getRole());
    
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    
        // Select customer by ID
        public User selectCustomer(int id) {
            User customer = null;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
    
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fName = rs.getString("fName");
                    boolean gender = rs.getBoolean("gender");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String dob = rs.getString("dob");
                    String avatar = rs.getString("avatar");
                    String address = rs.getString("address");
                    int status = rs.getInt("status");
                    String createdAt = rs.getString("createdAt");
                    String updatedAt = rs.getString("updatedAt");
                    int role = rs.getInt("role");
    
                    customer = new User(id, username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return customer;
        }
    
        // Select all customers
        public List<User> selectAllCustomers() {
            List<User> customers = new ArrayList<>();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
    
                ResultSet rs = preparedStatement.executeQuery();
    
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fName = rs.getString("fName");
                    boolean gender = rs.getBoolean("gender");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String dob = rs.getString("dob");
                    String avatar = rs.getString("avatar");
                    String address = rs.getString("address");
                    int status = rs.getInt("status");
                    String createdAt = rs.getString("createdAt");
                    String updatedAt = rs.getString("updatedAt");
                    int role = rs.getInt("role");
    
                    customers.add(new User(id, username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return customers;
        }
    
        // Update customer
        public boolean updateCustomer(User customer) throws SQLException {
            boolean rowUpdated;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.setString(3, customer.getfName());
                preparedStatement.setBoolean(4, customer.isGender());
                preparedStatement.setString(5, customer.getEmail());
                preparedStatement.setString(6, customer.getPhone());
                preparedStatement.setDate(7, Date.valueOf(customer.getDob()));
                preparedStatement.setString(8, customer.getAvatar());
                preparedStatement.setString(9, customer.getAddress());
                preparedStatement.setInt(10, customer.getStatus());
                preparedStatement.setDate(11, Date.valueOf(customer.getCreatedAt()));
                preparedStatement.setDate(12, Date.valueOf(customer.getUpdatedAt()));
                preparedStatement.setInt(13, customer.getRole());
                preparedStatement.setInt(14, customer.getId());
    
                rowUpdated = preparedStatement.executeUpdate() > 0;
            }
            return rowUpdated;
        }
    
        // Delete customer
        public boolean deleteCustomer(int id) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
                preparedStatement.setInt(1, id);
                rowDeleted = preparedStatement.executeUpdate() > 0;
            }
            return rowDeleted;
        }
    
        // Handle SQL exceptions
        private void printSQLException(SQLException ex) {
            for (Throwable e : ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }
    