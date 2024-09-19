package dal.post;

import dal.DAO;
import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostProcess extends DAO {
    public static PostProcess INSTANCE = new PostProcess();

    private PostProcess() {}

    public final List<Post> postList = new ArrayList<>();

    public void add(Post post) {
        String sql = "INSERT INTO [post](title, content, createdAt, updatedAt) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setDate(3, new java.sql.Date(post.getCreatedAt().getTime()));
            ps.setDate(4, new java.sql.Date(post.getUpdatedAt().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int postId) {
        String sql = "DELETE FROM [post] WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> read() {
        String sql = "SELECT * FROM [post] WHERE status = 1";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            postList.clear();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getDate("createdAt"));
                post.setUpdatedAt(rs.getDate("updatedAt"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public void update(Post post) {
        String sql = "UPDATE [post] SET title = ?, content = ?, updatedAt = ? WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setDate(3, new java.sql.Date(post.getUpdatedAt().getTime()));
            ps.setInt(4, post.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
