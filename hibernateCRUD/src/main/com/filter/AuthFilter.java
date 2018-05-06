package filter;

import model.User;
import org.apache.log4j.Logger;
import servlet.AddUserServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class AuthFilter implements Filter {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("auth logger init....");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        logger.info("Auth filter .................................................");
        boolean isAdmin = false;
        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");

        if (user != null) {
            logger.info("Auth filter: User " + user + " authorized");
            filterChain.doFilter(req, resp);
            return;
        }

        logger.warn("User not authorized, go back to login page");
        ((HttpServletRequest) req).getSession().setAttribute("message", "User name or password invalid");
        ((HttpServletResponse) resp).sendRedirect("/");
    }

    @Override
    public void destroy() {
        logger.info("auth logger destroy....");
    }

}
