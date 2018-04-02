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

@WebFilter(urlPatterns = "*")
public class AdminFilter implements Filter {
    private Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Log filter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestURI = request.getRequestURI();

        String beforeLast = StringUtils.substringBeforeLast(requestURI, "/");
        String afterLast = StringUtils.substringAfterLast(requestURI, "/");
        System.out.println("Path: " + beforeLast);

        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        if (beforeLast.equals("/admin") || afterLast.equals("admin")) {
            if (user.getRole().equals("admin")) {
                filterChain.doFilter(req, resp);
            } else {
                logger.warn("User " + user.getName() + " is not admin, redirect to login page");
                ((HttpServletRequest) req).getSession().removeAttribute("user");
                response.sendRedirect("/");
            }
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        System.out.println("Log filter destroy");
    }
}
