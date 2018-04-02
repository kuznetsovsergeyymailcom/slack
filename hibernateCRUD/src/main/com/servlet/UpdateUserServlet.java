package servlet;

import entitie.User;
import exception.AttemptToRemoveNotExistedItem;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/update", initParams = {@WebInitParam(name="jsp-file", value="/admin/update.jsp")})
public class UpdateUserServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(UpdateUserServlet.class);
    private UserService crudServiceImpl = UserServiceImpl.getInstance();
    private static int id = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter("id");

        if (ids.isEmpty()) {
            logger.warn("Attempt to update user using EMPTY id field ");
            resp.sendRedirect("/admin");
            return;
        }

        int id = Integer.parseInt(ids);

        if (crudServiceImpl.getUser(id) != null) {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String login = req.getParameter("login");
            String role = req.getParameter("role");

            if (this.id != id || name.isEmpty() || password.isEmpty() || login.isEmpty()) {
                logger.warn("Attempt to update user, one of fields is empty");
                resp.sendRedirect("/admin");
                return;
            }

            crudServiceImpl.updateUser(new User(id, name, password, login, role));
        } else {
            logger.warn("Attempt to update not existed user");
            try {
                throw new AttemptToRemoveNotExistedItem("Attempt to remove not existed item");
            } catch (AttemptToRemoveNotExistedItem attemptToRemoveNotExistedItem) {
                attemptToRemoveNotExistedItem.printStackTrace();
            }
        }

        resp.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter("id");

        if (ids.isEmpty()) {
            logger.warn("Attempt to update user using empty field");
            resp.sendRedirect("/admin");
            return;
        }
        int id = Integer.parseInt(ids);


        if (crudServiceImpl.getUser(id) == null) {
            logger.warn("Attempt to update not existed user");
            resp.sendRedirect("/admin");
            return;
        }

        User user = crudServiceImpl.getUser(id);
        req.getSession().setAttribute("editUser", user);

        this.id = id;
        req.getRequestDispatcher("/admin/update.jsp").forward(req, resp);

    }
}
