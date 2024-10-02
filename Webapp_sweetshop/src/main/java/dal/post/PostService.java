package dal.post;

import java.util.List;

public class PostService {
    private PostDAO postDAO;

    public PostService() {
        postDAO = new PostDAO();
    }

    public List<Post> getAllPosts() throws Exception {
        return postDAO.listAllPosts();
    }

    public void savePost(Post post) throws Exception {
        // Logic lưu bài viết
    }

    public void updatePost(Post post) throws Exception {
        // Logic cập nhật bài viết
    }

    public void deletePost(int id) throws Exception {
        // Logic xóa bài viết
    }
}
