//package dal.post;
//
//
//import dal.post.PostDAO;
//import model.Post;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet("/posts")
//public class PostServlet extends HttpServlet {
//    private PostDAO postDAO;
//
//    public void init() {
//        postDAO = new PostDAO();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response); // Chuyển tất cả yêu cầu POST thành GET để xử lý
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) action = "list";
//
//        try {
//            switch (action) {
//                case "new":
//                    showNewForm(request, response);
//                    break;
//                case "insert":
//                    insertPost(request, response);
//                    break;
//                case "edit":
//                    showEditForm(request, response);
//                    break;
//                case "update":
//                    updatePost(request, response);
//                    break;
//                case "delete":
//                    deletePost(request, response);
//                    break;
//                case "view":
//                    viewPost(request, response);
//                    break;
//                default:
//                    listPost(request, response);
//                    break;
//            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
//    }
//
//    // Hiển thị danh sách bài đăng
//    private void listPost(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<Post> listPost = postDAO.selectAllPosts();
//        request.setAttribute("listPost", listPost);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("postList.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // Hiển thị form tạo bài đăng mới
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("postForm.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    // Thêm bài đăng mới
//    private void insertPost(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//        int status = Integer.parseInt(request.getParameter("status"));
//        int userID = Integer.parseInt(request.getParameter("userID")); // Lấy từ session hoặc form
//
//        Post newPost = new Post();
//        newPost.setTitle(title);
//        newPost.setContent(content);
//        newPost.setStatus(status);
//        newPost.setUserID(userID);
//        newPost.setCreatedAt(new java.util.Date());
//        newPost.setUpdatedAt(new java.util.Date());
//
//        postDAO.insertPost(newPost);
//        response.sendRedirect("posts");
//    }
//
//    // Hiển thị form chỉnh sửa bài đăng
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Post existingPost = postDAO.selectPost(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("postForm.jsp");
//        request.setAttribute("post", existingPost);
//        dispatcher.forward(request, response);
//    }
//
//    // Cập nhật bài đăng
//    private void updatePost(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//        int status = Integer.parseInt(request.getParameter("status"));
//        int userID = Integer.parseInt(request.getParameter("userID")); // Lấy từ session hoặc form
//
//        Post post = new Post(id, title, content, status, new java.util.Date(), new java.util.Date(), userID);
//        postDAO.updatePost(post);
//        response.sendRedirect("posts");
//    }
//
//    // Xóa bài đăng
//    private void deletePost(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        postDAO.deletePost(id);
//        response.sendRedirect("posts");
//    }
//
//    // Xem chi tiết bài đăng
//    private void viewPost(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Post post = postDAO.selectPost(id);
//        request.setAttribute("post", post);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("postView.jsp");
//        dispatcher.forward(request, response);
//    }
//}
