package dal.staff;

import dal.DAO;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffProcess extends DAO {

    public StaffProcess() {
        // Initialization can be done if needed
    }

    private Staff createStaffFromResultSet(ResultSet rs) throws SQLException {
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
        return staff;
    }

    private List<Staff> getStaffByCondition(String condition) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 2 " + condition + ";";
        try (PreparedStatement ps = this.connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                staffList.add(createStaffFromResultSet(rs));
            }
        } catch (SQLException e) {
            // Consider throwing a custom exception here
            e.printStackTrace();
        }
        return staffList;
    }


    public List<Staff> getAllStaff() {
        return getStaffByCondition(""); // Lấy tất cả nhân viên
    }

    public List<Staff> getStaffActive() {
        return getStaffByCondition("AND status = 1");
    }

    public List<Staff> getStaffDisable() {
        return getStaffByCondition("AND status = 0");
    }

    public List<Staff> searchStaff(String keyword) {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 2 AND username LIKE ? ORDER BY username ASC;";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    staffs.add(createStaffFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            // Consider throwing a custom exception here
            e.printStackTrace();
        }
        return staffs;
    }

    public Staff getStaffById(String id) {
        Staff staff = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try  {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                staff = createStaffFromResultSet(rs);
            }
        }catch (SQLException e) {
            // Consider throwing a custom exception here
            e.printStackTrace();
        }
        return staff;

    }


    public boolean add(Staff staff) {
        String sql = "INSERT INTO user (username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
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
            ps.setDate(11, staff.getCreatedAt());
            ps.setDate(12, staff.getUpdatedAt());
            ps.setInt(13, staff.getRole());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // Consider throwing a custom exception here
            e.printStackTrace();
            return false;
        }
    }
}
