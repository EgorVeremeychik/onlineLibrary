package filter;

import dao.impl.mysql.GenreDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

/**
 * Created by EgorVeremeychik on 19.04.2016.
 */

@WebFilter(urlPatterns = "*")
public class ContentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        GenreDAO genreDAO = GenreDAO.getInstance();
        int start = 0;
        List genreList = genreDAO.readAll(start);
        request.setAttribute("genreList", genreList);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
