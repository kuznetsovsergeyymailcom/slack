package servlet;

import entitie.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class ShowUsersServlet extends HttpServlet {
    private UserService crudServiceImpl = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = crudServiceImpl.getAllUsers();
        List<String> list_login = new ArrayList<>();
        List<Integer> list_ids = new ArrayList<>();

        req.setAttribute("users", allUsers);

        for(User user : allUsers){
            list_ids.add(user.getId());
            list_login.add(user.getLogin());
        }

        req.getSession().setAttribute("logins", list_login);
        req.getSession().setAttribute("ids", list_ids);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showUsers.jsp");
        requestDispatcher.forward(req, resp);
    }

}
