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
import java.util.List;

@WebServlet(urlPatterns = "/update")
public class UpdateUserServlet extends HttpServlet{
    private static int id = 0;

    private UserService crudServiceImpl = UserServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");

        if(ids.isEmpty()){
            resp.sendRedirect("users");
            return;
        }
        int id = Integer.parseInt(ids);

        List<Integer> list_of_ids = (List<Integer>)req.getSession().getAttribute("ids");
        if(this.id != id){
            resp.sendRedirect("users");
            return;
        }

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String login = req.getParameter("login");

        this.crudServiceImpl.editUser(id, new User(name,password,login));
        resp.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> list_of_ids = (List<Integer>)req.getSession().getAttribute("ids");

        String ids = req.getParameter("id");

        if(ids.isEmpty()){
            resp.sendRedirect("users");
            return;
        }
        int id = Integer.parseInt(ids);

        if(!list_of_ids.contains(id)){
            resp.sendRedirect("users");
            return;
        }

        User user = this.crudServiceImpl.getUser(id);
        req.setAttribute("user", user);

        this.id = id;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("update.jsp");
        requestDispatcher.forward(req, resp);
    }
}
