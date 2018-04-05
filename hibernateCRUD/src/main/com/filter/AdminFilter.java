package filter;

import entitie.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import servlet.AddUserServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        if(user == null){
            response.sendRedirect("/");
            return;
        }

        if (user.getRole().equals("admin")) {
            logger.info("Admin filter, do filter, ADMIN attempt to login in");
            filterChain.doFilter(req, resp);
        } else {
            logger.warn("User " + user.getName() + " is NOT ADMIN, redirect to login page");
            ((HttpServletRequest) req).getSession().removeAttribute("user");
            response.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
        logger.info("Admin filter destroy..");

    }
}
