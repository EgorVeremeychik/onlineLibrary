package filter;

import entity.impl.User;
import manager.PagesEnum;
import manager.PagesManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EgorVeremeychik on 24.05.2016.
 */

@WebFilter(servletNames = {"Login","Search","Main"})
public class AccessFilter implements Filter {
    private static final String USER = "user";
    private static final String LOGIN = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = null;
        String login = null;
        User user = null;
        try {
            user = (User) request.getSession().getAttribute(USER);
            if (user == null) {
                login = request.getParameter(LOGIN);
                if (login == null || login.equals("")) {
                    path = PagesManager.getPage(PagesEnum.INDEX);
                    response.sendRedirect(path);
                }else{
                    filterChain.doFilter(request, response);
                }
            }else{
                filterChain.doFilter(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
