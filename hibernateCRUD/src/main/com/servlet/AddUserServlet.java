package servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add")
public class AddUserServlet extends HttpServlet {
    private UserService crudServiceImpl = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("add.jsp");
    }

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
