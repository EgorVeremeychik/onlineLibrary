package controller;

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
 * Created by EgorVeremeychik on 22.04.2016.
 */

@WebServlet(name = "Logout", urlPatterns = "/logout")
public class Logout extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Logout.class);
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
        try {
            request.getSession().setAttribute(USER, null);
            path = PagesManager.getPage(PagesEnum.AUTHORIZATION);
            LOG.info("LOGOUT SUCCESS!");
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException e) {
            LOG.error(e);
        }
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