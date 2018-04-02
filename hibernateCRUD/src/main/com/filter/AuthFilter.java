package filter;

import entitie.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;
import servlet.AddUserServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("auth logger init....");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Auth logger started ... ");
        String requestURI = ((HttpServletRequest) req).getRequestURI();
        String afterLast = StringUtils.substringAfterLast(requestURI, "/");

        if (afterLast.isEmpty() || afterLast.equals("login")) {
            logger.info("Auth logger empty or going to login page ... ");
            filterChain.doFilter(req, resp);
            return;
        }

        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        System.out.println("Auth filter, User: " + user);
        if (user != null) {
            filterChain.doFilter(req, resp);
            return;
        }

        logger.warn("User not authorized, go back to login page");
        ((HttpServletRequest) req).getSession().removeAttribute("user");
        ((HttpServletResponse) resp).sendRedirect("/");
    }

    @Override
    public void destroy() {

    }
}
