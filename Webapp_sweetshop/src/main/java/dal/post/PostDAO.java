//package dal.post;
//
//
//import model.Post;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PostDAO {
//    private String jdbcURL = "jdbc:mysql://localhost:3306/shopcake";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "password";
//
//    private static final String INSERT_POST_SQL = "INSERT INTO post (title, content, status, createdAt, updatedAt, userID) VALUES (?, ?, ?, ?, ?, ?)";
//    private static final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE id = ?";
//    private static final String SELECT_ALL_POSTS = "SELECT * FROM post ORDER BY createdAt DESC";
//    private static final String UPDATE_POST_SQL = "UPDATE post SET title = ?, content = ?, status = ?, updatedAt = ?, userID = ? WHERE id = ?";
//    private static final String DELETE_POST_SQL = "DELETE FROM post WHERE id = ?";
//
//    public PostDAO() {}
//
//    protected Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); // Sử dụng driver phù hợp
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    // Thêm bài đăng mới
//    public void insertPost(Post post) throws SQLException {
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST_SQL)) {
//            preparedStatement.setString(1, post.getTitle());
//            preparedStatement.setString(2, post.getContent());
//            preparedStatement.setInt(3, post.getStatus());
//            preparedStatement.setDate(4, new java.sql.Date(post.getCreatedAt().getTime()));
//            preparedStatement.setDate(5, new java.sql.Date(post.getUpdatedAt().getTime()));
//            preparedStatement.setInt(6, post.getUserID());
//            preparedStatement.executeUpdate();
//        }
//    }
//
//    // Lấy bài đăng theo ID
//    public Post selectPost(int id) {
//        Post post = null;
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                int status = rs.getInt("status");
//                Date createdAt = rs.getDate("createdAt");
//                Date updatedAt = rs.getDate("updatedAt");
//                int userID = rs.getInt("userID");
//                post = new Post(id, title, content, status, createdAt, updatedAt, userID);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return post;
//    }
//
//    // Lấy tất cả bài đăng
//    public List<Post> selectAllPosts() {
//        List<Post> posts = new ArrayList<>();
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POSTS)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                int status = rs.getInt("status");
//                Date createdAt = rs.getDate("createdAt");
//                Date updatedAt = rs.getDate("updatedAt");
//                int userID = rs.getInt("userID");
//                posts.add(new Post(id, title, content, status, createdAt, updatedAt, userID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return posts;
//    }
//
//    // Cập nhật bài đăng
//    public boolean updatePost(Post post) throws SQLException {
//        boolean rowUpdated;
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POST_SQL)) {
//            preparedStatement.setString(1, post.getTitle());
//            preparedStatement.setString(2, post.getContent());
//            preparedStatement.setInt(3, post.getStatus());
//            preparedStatement.setDate(4, new java.sql.Date(post.getUpdatedAt().getTime()));
//            preparedStatement.setInt(5, post.getUserID());
//            preparedStatement.setInt(6, post.getId());
//
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    // Xóa bài đăng
//    public boolean deletePost(int id) throws SQLException {
//        boolean rowDeleted;
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POST_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//}
