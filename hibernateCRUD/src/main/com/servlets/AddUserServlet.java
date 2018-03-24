package servlets;

import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add")
public class AddUserServlet extends HttpServlet {
    UserService crudServiceImpl = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");

        List<String> logins = (List<String>) req.getSession().getAttribute("logins");

        if(!name.isEmpty() && !password.isEmpty() && !login.isEmpty() && !logins.contains(login)){
            this.crudServiceImpl.addUser(name,password,login);
        }

        resp.sendRedirect("users");
    }
}
