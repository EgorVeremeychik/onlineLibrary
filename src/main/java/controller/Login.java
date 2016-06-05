package controller;

import entity.impl.User;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EgorVeremeychik on 15.04.2016.
 */

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Login.class);
    private static final String LOGIN = "login";
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path;
        User user = (User)request.getSession().getAttribute(USER);
        if(user == null){
            String login = request.getParameter(LOGIN);
            user = new User(login);
            request.getSession().setAttribute(USER, user);
            LOG.info("LOGIN SUCCESS!");
        }
        path = PagesManager.getPage(PagesEnum.START_BOOK_CATALOG);
        response.sendRedirect(path);

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
