package dal.post;


import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sweetshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_POSTS_SQL = "INSERT INTO posts (title, content) VALUES (?, ?)";
    private static final String SELECT_POST_BY_ID = "SELECT id, title, content FROM posts WHERE id = ?";
    private static final String SELECT_ALL_POSTS = "SELECT * FROM posts";
    private static final String UPDATE_POSTS_SQL = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
    private static final String DELETE_POSTS_SQL = "DELETE FROM posts WHERE id = ?";

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

    // Lưu bài viết
    public void savePost(Post post) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POSTS_SQL)) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy bài viết theo ID
    public Post getPostById(int id) {
        Post post = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                post = new Post(id, title, content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    // Lấy tất cả bài viết
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POSTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                posts.add(new Post(id, title, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // Cập nhật bài viết
    public void updatePost(Post post) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POSTS_SQL)) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setInt(3, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa bài viết
    public void deletePost(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POSTS_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
