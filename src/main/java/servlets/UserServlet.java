package servlets;

import dao.UserDao;
import dbFactory.DaoFactory;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("id");
        try {
            int id = Integer.parseInt(str);
            UserDao userDao = DaoFactory.getUserDao();
            User user = userDao.getUser(id);
            if (user != null) {
                user.toString();
                resp.getWriter().println("user: " + user.getFirst_name() + " " + user.getLast_name());
                resp.getWriter().println("email: " + user.getEmail());
                resp.setStatus(202);
            } else {
                resp.setStatus(404);
                resp.getWriter().println("user not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        resp.getWriter().println("users!");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        UserDao userdao = DaoFactory.getUserDao();
        User user = new User(firstName, lastName, email);
        if (userdao.create(user)) {
            resp.setStatus(202);
            resp.getWriter().print("Create a new user.");
        } else {
            resp.setStatus(400);
            resp.getWriter().print("Failed to create new user.");
        }
    }
}
