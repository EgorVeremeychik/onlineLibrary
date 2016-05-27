package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by EgorVeremeychik on 19.04.2016.
 */

@WebFilter(urlPatterns = "*")
public class CharsetFilter implements Filter {
    private static final String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
