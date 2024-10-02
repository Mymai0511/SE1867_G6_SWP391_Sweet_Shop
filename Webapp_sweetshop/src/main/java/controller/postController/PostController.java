package controller.postController;


import dal.post.PostDAO;
import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class PostController extends HttpServlet {
    private PostDAO postDAO;

    @Override
    public void init() {
        postDAO = new PostDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "new":
                showNewPostForm(request, response);
                break;
            case "edit":
                showEditPostForm(request, response);
                break;
            case "delete":
                deletePost(request, response);
                break;
            default:
                listPosts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            default:
                listPosts(request, response);
                break;
        }
    }

    // Hiển thị danh sách bài viết
    private void listPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAO.getAllPosts();
        request.setAttribute("posts", posts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/listPosts.jsp");
        dispatcher.forward(request, response);
    }

    // Hiển thị form tạo bài viết mới
    private void showNewPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/createPost.jsp");
        dispatcher.forward(request, response);
    }

    // Hiển thị form cập nhật bài viết
    private void showEditPostForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Post existingPost = postDAO.getPostById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/updatePost.jsp");
        request.setAttribute("post", existingPost);
        dispatcher.forward(request, response);
    }

    // Xử lý tạo bài viết mới
    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Post newPost = new Post(title, content);
        postDAO.savePost(newPost);
        response.sendRedirect("posts");
    }

    // Xử lý cập nhật bài viết
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Post post = new Post(id, title, content);
        postDAO.updatePost(post);
        response.sendRedirect("posts");
    }

    // Xử lý xóa bài viết
    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        postDAO.deletePost(id);
        response.sendRedirect("posts");
    }
}


