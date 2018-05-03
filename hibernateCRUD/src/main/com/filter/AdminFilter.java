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
import java.io.PrintWriter;
import java.util.Iterator;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Admin filter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        logger.info("Admin filter do filter..");
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        if (user == null) {
            ((HttpServletRequest) req).getSession().setAttribute("message", "Please login as administrator to ");

            response.sendRedirect("/user");
            return;
        }

        if (isAdminCheck(user.getRoles().iterator())) {
            logger.info("Admin filter, do filter, ADMIN attempt to login in");
            filterChain.doFilter(req, resp);
        } else {
            logger.warn("User " + user.getName() + " is NOT ADMIN, redirect to login page");
            ((HttpServletRequest) req).getSession().setAttribute("message", "You have no rights to enter requested page, " +
                    "\nPlease login as administrator");

            response.sendRedirect("/user");
        }
    }

    @Override
    public void destroy() {
        logger.info("Admin filter destroy..");

    }

    private boolean isAdminCheck(Iterator<Role> iterator) {
        boolean admin = false;
        while (iterator.hasNext()) {
            if ("admin".equalsIgnoreCase(iterator.next().getRole())) {
                admin = true;
                break;
            }
        }
        return admin;
    }
}
