package servlet;

import exception.AttemptToRemoveNotExistedItem;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/remove")
public class RemoveUserServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddUserServlet.class);
    private UserService crudServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqId = req.getParameter("id");

        if (reqId.isEmpty()) {
            logger.warn("Attempt to remove user using EMPTY id field ");
            resp.sendRedirect("/users");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        if (crudServiceImpl.getUser(id) != null) {
            crudServiceImpl.removeUser(id);
        } else {
            logger.warn("Attempt to remove user with not existed id: " + id);
            try {
                throw new AttemptToRemoveNotExistedItem("User with id: " + id + " not existed!");
            } catch (AttemptToRemoveNotExistedItem e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/admin");
    }
}
