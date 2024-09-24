package dal.staff;

import dal.DAO;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffProcess extends DAO {
    private static StaffProcess instance;

    public static StaffProcess Instance() {
        if (instance == null) {
            instance = new StaffProcess();
        }
        return instance;
    }


    private StaffProcess() {}

    public final List<Staff> staffList = new ArrayList<>();

    // lấy cac user có role = 2 -staff
    public List<Staff> read() {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role IN (2);";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            staffs.clear();
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
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    // lấy cac user có role = 2 -staff and active
    public List<Staff> gettStaffActive() {
        List<Staff> activeStaff = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 2 AND status = 1;";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            activeStaff.clear();
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
                activeStaff.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeStaff;
    }

    // lấy cac user có role = 2 -staff and disable
    public List<Staff> gettStaffDisable() {
        List<Staff> disableStaff = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 2 AND status = 0;";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            disableStaff.clear();
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
                disableStaff.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disableStaff;
    }

    // Tìm staff theo tên
    public List<Staff> searchStaff(String keyword) {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 2 AND username LIKE ? ORDER BY username ASC;";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%"); // Thêm dấu % để tìm kiếm chứa từ khóa
            ResultSet rs = ps.executeQuery();
            staffs.clear();
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
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    // add a new staff member (User) to the database
    public boolean add(Staff staff) {
         String sql = "INSERT INTO user " +
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
            ps.setDate(11, staff.getCreatedAt());
            ps.setDate(12, staff.getUpdatedAt());
            ps.setInt(13, staff.getRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        // Tạo một đối tượng Staff mới
        Staff newStaff = new Staff();
        newStaff.setUsername("my");
        newStaff.setPassword("password123");
        newStaff.setFullName("John Doe");
        newStaff.setGender(true); // true = Nam, false = Nữ
        newStaff.setEmail("john@example.com");
        newStaff.setPhone("0123456789");
        newStaff.setDob(Date.valueOf("1990-01-01"));
        newStaff.setAvatar("avatar.jpg");
        newStaff.setAddress("123 Main Street");
        newStaff.setStatus(1); // 1 = active, 0 = disabled
        newStaff.setRole(2); // role = 2 cho nhân viên

        // Gọi phương thức add của StaffProcess để thêm Staff
        boolean result = StaffProcess.Instance().add(newStaff);

        if (result) {
            System.out.println("Thêm nhân viên thành công!");
        } else {
            System.out.println("Thêm nhân viên thất bại.");
        }
        // Gọi hàm read để lấy danh sách nhân viên
        List<Staff> staffList = StaffProcess.Instance().read();

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















