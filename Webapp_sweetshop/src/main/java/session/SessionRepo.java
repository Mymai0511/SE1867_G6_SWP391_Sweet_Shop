package session;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class SessionRepo {
    public static SessionRepo INSTANCE = new SessionRepo();

    private SessionRepo() {}

    public static User getUser(HttpServletRequest request, HttpServletResponse response) {
        return (User) request.getSession().getAttribute("user");
    }

    public static void setUser(HttpServletRequest request, HttpServletResponse response, User user) {
        request.getSession().setAttribute("user", user);
    }
}
