package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Attempt to log out.....");

        req.getSession().removeAttribute("user");
        req.getSession().setAttribute("message", "You successfully logout");
        resp.sendRedirect("/");
    }
}
