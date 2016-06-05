package controller;

import entity.impl.Book;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;
import service.BooksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EgorVeremeychik on 04.06.2016.
 */

@WebServlet(name = "AddBook", urlPatterns = "/AddBook")
public class AddBook extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddBook.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = null;
        Book book = null;
        boolean resultOperation = false;
        resultOperation = BooksService.addBook(book);
        path = PagesManager.getPage(PagesEnum.START_BOOK_CATALOG);
        response.sendRedirect(path);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
