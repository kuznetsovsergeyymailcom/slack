package servlet;

import model.User;
import org.apache.log4j.Logger;
import role.Role;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddUserServlet.class);
    private UserService crudServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAdmin = false;
        req.getSession().removeAttribute("message");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = crudServiceImpl.getUser(name);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);

                System.out.println("Roles: ----------------------------------------");
                Iterator<Role> iterator = user.getRoles().iterator();
                while (iterator.hasNext()) {
                    if("admin".equalsIgnoreCase(iterator.next().getRole())){
                        isAdmin = true;
                        break;
                    }
                }

                if (isAdmin) {
                    resp.sendRedirect("/admin");
                    logger.info("user authorized, forward to /admin/users");
                    return;
                } else {
                    logger.info("user authorized, forward to /user");
                    resp.sendRedirect("/user");
                    return;
                }
            }
        }
        logger.warn("user unauthorized, wrong password or user not found");
        req.getSession().setAttribute("message", "User name or password invalid");
        req.getRequestDispatcher("/").forward(req, resp);

    }
}
