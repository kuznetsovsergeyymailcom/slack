package servlet;

import entitie.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddUserServlet.class);
    private UserService crudServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = crudServiceImpl.getUser(name);
        if(user != null){
            if(user.getPassword().equals(password)){
                req.getSession().setAttribute("user", user);
                if(user.getRole().equals("admin")){
                    resp.sendRedirect("/admin");
                    logger.info("user authorized, forward to /admin/users");
                    return;
                }else{
                    resp.sendRedirect("/user");
                    return;
                }
            }
        }
        logger.warn("user unauthorized, wrong password or user not found");

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
