package servlets;

import services.UserService;
import services.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/remove")
public class RemoveUserServlet extends HttpServlet{
    UserService crudServiceImpl = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id_from_req = req.getParameter("id");

        if(id_from_req.isEmpty()){
            resp.sendRedirect("users");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));

        List<Integer> list_of_ids = (List<Integer>)req.getSession().getAttribute("ids");

        if(list_of_ids.contains(id)){
            this.crudServiceImpl.removeUser(id);
        }

        resp.sendRedirect("users");
    }
}
