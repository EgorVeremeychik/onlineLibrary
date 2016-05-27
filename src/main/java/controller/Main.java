package controller;

import command.ICommand;
import command.impl.MainCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EgorVeremeychik on 18.04.2016.
 */

@WebServlet(name = "Main", urlPatterns = "/main")
public class Main extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Main.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String path = null;
            ICommand command = new MainCommand();
            path = command.execute(request);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
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
