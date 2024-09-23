package dal.staff;

import dal.DAO;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffProcess extends DAO {
    public static StaffProcess Instance = new StaffProcess();

    private StaffProcess() {}

    public final List<Staff> staffList = new ArrayList<>();

    public List<Staff> read() {
        String sql = "SELECT * FROM user WHERE role IN (2) ;";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            staffList.clear();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fName"));
                staff.setGender(rs.getBoolean("gender"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setDob(rs.getDate("dob"));
                staff.setAvatar(rs.getString("avatar"));
                staff.setAddress(rs.getString("address"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getDate("createdAt"));
                staff.setUpdatedAt(rs.getDate("updatedAt"));
                staff.setRole(rs.getInt("role"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public void add(Staff staff) {
        String sql = "INSERT INTO staff " +
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
            ps.setString(1, staff.getUsername());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getFullName());
            ps.setBoolean(4, staff.isGender());
            ps.setString(5, staff.getEmail());
            ps.setString(6, staff.getPhone());
            ps.setDate(7, staff.getDob());
            ps.setString(8, staff.getAvatar());
            ps.setString(9, staff.getAddress());
            ps.setInt(10, staff.getStatus());
            ps.setDate(11, new java.sql.Date(staff.getCreatedAt().getTime()));
            ps.setDate(12, new java.sql.Date(staff.getUpdatedAt().getTime()));
            ps.setInt(13, staff.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        List<Staff> staffList = StaffProcess.Instance.read();

        // In ra thông tin của từng nhân viên trong danh sách
        for (Staff staff : staffList) {
            System.out.println("ID: " + staff.getId());
            System.out.println("Username: " + staff.getUsername());
            System.out.println("Full Name: " + staff.getFullName());
            System.out.println("Gender: " + (staff.isGender() ? "Male" : "Female"));
            System.out.println("Email: " + staff.getEmail());
            System.out.println("Phone: " + staff.getPhone());
            System.out.println("DOB: " + staff.getDob());
            System.out.println("Avatar: " + staff.getAvatar());
            System.out.println("Address: " + staff.getAddress());
            System.out.println("Status: " + staff.getStatus());
            System.out.println("Created At: " + staff.getCreatedAt());
            System.out.println("Updated At: " + staff.getUpdatedAt());
            System.out.println("-------------------------");
        }
    }

}















