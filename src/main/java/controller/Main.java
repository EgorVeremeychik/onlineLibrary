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
        String path = null;
        int page = 0;
        int bookCount = 0;
        int startPage = 0;
        List<Book> books = null;
        Integer selectedGenreId = 0;
        try {
            if (request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }else {
                page = 1;
            }
            startPage = page - 1;
            HttpSession session = request.getSession();
            if (request.getParameter("genreID") != null) {
                selectedGenreId = Integer.valueOf(request.getParameter("genreID"));
            } else{
                selectedGenreId = session.getAttribute("genreID") != null ?
                        Integer.valueOf(session.getAttribute("alreadySaw").toString()) : selectedGenreId;
            }
            session.setAttribute("alreadySaw", selectedGenreId);
            bookCount = selectedGenreId == 0 ? BooksService.getNumAllBooks() :
                    BooksService.getNumBooksByGenreID(selectedGenreId);
            books = selectedGenreId == 0 ? BooksService.getAllBooks(startPage) :
                    BooksService.getBooksByGenreID(selectedGenreId,startPage);
            request.setAttribute("books",books);
            request.setAttribute("booksCount",bookCount);
            request.setAttribute("startPage", startPage);
            request.setAttribute("page", page);
            bookCount = bookCount%6 == 0 ? (bookCount - 1) : bookCount;
            request.setAttribute("lastPage", bookCount/6);
            request.setAttribute("genreID", selectedGenreId);
            path = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
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
