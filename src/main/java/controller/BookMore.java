package controller;

import com.google.gson.Gson;
import entity.impl.*;
import org.apache.log4j.Logger;
import service.AuthorService;
import service.BooksService;
import service.GenreService;
import service.PublisherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by EgorVeremeychik on 03.06.2016.
 */
@WebServlet(name = "BookMore", urlPatterns = "/bookMore")
public class BookMore extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(BookMore.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookID;
        List<Genre> genresList = null;
        List<Author> authorsList = null;
        List<Publisher> publishersList = null;
        Book book = null;
        bookID = Integer.parseInt(request.getParameter("bookID"));
        book = BooksService.getBookByID(bookID);
        genresList = GenreService.getAllGenre();
        authorsList = AuthorService.getAllAuthor();
        publishersList = PublisherService.getAllPublisher();
        BookSettings bookSettings = new BookSettings(book,genresList,authorsList,publishersList);
        String json = new Gson().toJson(bookSettings);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
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