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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */

@WebServlet(name = "Search", urlPatterns = "/search")
public class Search extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Search.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = null;
        List<Book> books = null;
        int page = 0;
        int bookCount = 0;
        int startPage = 0;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("alreadySaw", null);
            if (request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }else {
                page = 1;
            }
            String searchString = request.getParameter("search_string");
            String searchOption = request.getParameter("search_option");
            startPage = page - 1;
            bookCount = BooksService.getNumBooksFound(searchString,searchOption);
            books = BooksService.findBooks(searchString,searchOption,startPage);
            LOG.info("Find query processed");
            request.setAttribute("books", books);
            request.setAttribute("startPage", startPage);
            request.setAttribute("page", page);
            request.setAttribute("booksCount", bookCount);
            request.setAttribute("search_option",searchOption);
            request.setAttribute("search_string",searchString);
            bookCount = bookCount%6 == 0 ? (bookCount - 1) : bookCount;
            request.setAttribute("lastPage", bookCount/6);
            path = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
            LOG.info("LOGIN SUCCESS!");
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
