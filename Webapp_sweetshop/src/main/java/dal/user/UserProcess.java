package dal.user;

import dal.DAO;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProcess extends DAO {
    public static UserProcess Instance = new UserProcess();

    private UserProcess() {}

    public final List<User> UserList = new ArrayList<>();

    // lấy cac user có role = 3 or 2
    public List<User> read() {
        String sql = "SELECT * FROM user WHERE role IN (2, 3);";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UserList.clear();
            while (rs.next()) {
                User User = new User();
                User.setId(rs.getInt("id"));
                User.setUsername(rs.getString("username"));
                User.setPassword(rs.getString("password"));
                User.setFullName(rs.getString("fName"));
                User.setGender(rs.getBoolean("gender"));
                User.setEmail(rs.getString("email"));
                User.setPhone(rs.getString("phone"));
                User.setDob(rs.getDate("dob"));
                User.setAvatar(rs.getString("avatar"));
                User.setAddress(rs.getString("address"));
                User.setStatus(rs.getInt("status"));
                User.setCreatedAt(rs.getDate("createdAt"));
                User.setUpdatedAt(rs.getDate("updatedAt"));
                User.setRole(rs.getInt("role"));
                UserList.add(User);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserList;
    }

    // lấy cac user có role = 2 - User

    public void add(User User) {
        String sql = "INSERT INTO User " +
                "(username, " +
                "password, " +
                "fName, " +
                "gender, " +
                "email, " +
                "phone, " +
                "dob, " +
                "avatar, " +
                "address, " +
                "status, " +
                "createdAt, " +
                "updatedAt, " +
                "role) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, User.getUsername());
            ps.setString(2, User.getPassword());
            ps.setString(3, User.getFullName());
            ps.setBoolean(4, User.isGender());
            ps.setString(5, User.getEmail());
            ps.setString(6, User.getPhone());
            ps.setDate(7, User.getDob());
            ps.setString(8, User.getAvatar());
            ps.setString(9, User.getAddress());
            ps.setInt(10, User.getStatus());
            ps.setDate(11, new java.sql.Date(User.getCreatedAt().getTime()));
            ps.setDate(12, new java.sql.Date(User.getUpdatedAt().getTime()));
            ps.setInt(13, User.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new user and return id after add
     *
     * @param User information user
     * @return new id of user after add and null if add not success
     */
    public String addAndReturnId(User User) {
        String sql = "{CALL insertUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        String id = null;
        try{
            CallableStatement cs = this.connection.prepareCall(sql);
            cs.setString(1, User.getUsername());
            cs.setString(2, User.getPassword());
            cs.setString(3, User.getFullName());
            cs.setBoolean(4, User.isGender());
            cs.setString(5, User.getEmail());
            cs.setString(6, User.getPhone());
            cs.setDate(7, User.getDob());
            cs.setString(8, User.getAvatar());
            cs.setString(9, User.getAddress());
            cs.setInt(10, User.getStatus());
            cs.setDate(11, new java.sql.Date(User.getCreatedAt().getTime()));
            cs.setDate(12, new java.sql.Date(User.getUpdatedAt().getTime()));
            cs.setInt(13, User.getRole());
            boolean hasResult = cs.execute();
            if (hasResult) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                    id = rs.getString("newUserID");
                }
                rs.close();
            }
            cs.close();
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return id;
    }

    /**
     * find a user by username or password and password
     *
     * @param username username or password
     * @param password password
     * @return new user if info is correct else null
     */
    public User findUser(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM shopcake.user WHERE (username = ? OR email = ?) AND password = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fName"));
                user.setGender(rs.getBoolean("gender"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setDob(rs.getDate("dob"));
                user.setAvatar(rs.getString("avatar"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getInt("status"));
                user.setCreatedAt(rs.getDate("createdAt"));
                user.setUpdatedAt(rs.getDate("updatedAt"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return user;
    }


//    public void update(User user) {
//        String sql = "UPDATE [post] " +
//                "SET title = ?, " +
//                "content = ?, " +
//                "updatedAt = ? " +
//                "WHERE id = ?";
//        try {
//            PreparedStatement ps = this.connection.prepareStatement(sql);
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getFName());
//            ps.setBoolean(4, user.isGender());
//            ps.setString(5, user.getEmail());
//            ps.setString(6, user.getPhone());
//            ps.setDate(7, user.getDob());
//            ps.setString(8, user.getAvatar());
//            ps.setString(9, user.getAddress());
//            ps.setInt(10, user.getStatus());
//            ps.setDate(11, new java.sql.Date(user.getCreatedAt().getTime()));
//            ps.setDate(12, new java.sql.Date(user.getUpdatedAt().getTime()));
//            ps.setInt(13, user.getRole());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        // Gọi hàm read để lấy danh sách nhân viên
        User User = UserProcess.Instance.findUser("customer", "12345");

        // In ra thông tin của từng nhân viên trong danh sách
            System.out.println("ID: " + User.getId());
            System.out.println("Username: " + User.getUsername());
            System.out.println("Full Name: " + User.getFullName());
            System.out.println("Gender: " + (User.isGender() ? "Male" : "Female"));
            System.out.println("Email: " + User.getEmail());
            System.out.println("Phone: " + User.getPhone());
            System.out.println("DOB: " + User.getDob());
            System.out.println("Avatar: " + User.getAvatar());
            System.out.println("Address: " + User.getAddress());
            System.out.println("Status: " + User.getStatus());
            System.out.println("Created At: " + User.getCreatedAt());
            System.out.println("Updated At: " + User.getUpdatedAt());
            System.out.println("-------------------------");
    }



}















