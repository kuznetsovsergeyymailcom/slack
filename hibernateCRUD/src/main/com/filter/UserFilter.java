package filter;

import model.User;
import org.apache.log4j.Logger;
import role.Role;
import servlet.AddUserServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebFilter(urlPatterns = "/user/*")
public class UserFilter implements Filter {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("User filter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        if (user == null) {
            ((HttpServletRequest) req).getSession().setAttribute("message", "Please login as administrator to ");

            response.sendRedirect("/user");
            return;
        }

        if (isUserCheck(user.getRoles().iterator())) {
            logger.info("User filter, do filter, USER attempt to login in");
            chain.doFilter(req, resp);
        } else {
            logger.warn("User " + user.getName() + " is NOT USER, redirect to login page");
            ((HttpServletRequest) req).getSession().setAttribute("message", "You have no rights to enter requested page, " +
                    "\nPlease login as user");

            response.sendRedirect("/admin");
        }

    }

    @Override
    public void destroy() {
        logger.info("User filter destroy");
    }

    private boolean isUserCheck(Iterator<Role> iterator) {
        boolean admin = false;
        while (iterator.hasNext()) {
            if ("user".equalsIgnoreCase(iterator.next().getRole())) {
                admin = true;
                break;
            }
        }
        return admin;
    }
}
