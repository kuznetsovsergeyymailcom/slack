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

@WebServlet(urlPatterns = "/admin/add")
public class AddUserServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddUserServlet.class);
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("message");
        req.getRequestDispatcher("/admin/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("message");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        String[] role = req.getParameterValues("roles");

        if (!name.isEmpty() && !password.isEmpty() && !login.isEmpty()) {
            User user = userService.getUser(name);
            if (user == null) {

                userService.addUser(name, password, login, role);
            } else {
                req.getSession().setAttribute("message", "User with name: " + name + " cannot be added, it already exists!");
            }
            logger.warn("Attempt to add user with empty fields");

        } else {
            logger.warn("Attempt to add user with empty fields");
            req.getSession().setAttribute("message", "User cannot be added, one or more fields is empty");
        }

        resp.sendRedirect("/admin");
    }
}
