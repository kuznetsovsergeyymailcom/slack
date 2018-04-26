package servlet;

import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class ShowUsersServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(ShowUsersServlet.class);
    private UserService crudServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Show users servlet do post method");
        List<User> allUsers = crudServiceImpl.getAllUsers();

        logger.info("Show users servlet do post method, session has admin attribute, equals to yes");
        req.getSession().setAttribute("users", allUsers);
        req.getRequestDispatcher("/admin/showUsers.jsp").forward(req, resp);

    }
}
