package command.impl;

import command.ICommand;
import entity.impl.Book;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;
import service.BooksService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EgorVeremeychik on 04.05.2016.
 */
public class MainCommand implements ICommand {
    private static final Logger LOG = Logger.getLogger(MainCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String result;
        int bookCount;
        List<Book> books = null;
        long selectedGenreId = 0;
        int page = (request.getParameter("page") != null) ?
                Integer.parseInt(request.getParameter("page")) : 1;
        int startPage = page - 1;
        HttpSession session = request.getSession();
        if (request.getParameter("genreID") != null) {
            selectedGenreId = Long.valueOf(request.getParameter("genreID"));
        } else{
            selectedGenreId = session.getAttribute("genreID") != null ?
                    Long.valueOf(session.getAttribute("alreadySaw").toString()) : selectedGenreId;
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
        bookCount = bookCount%3 == 0 ? (bookCount - 1) : bookCount;
        request.setAttribute("lastPage", bookCount/3);
        request.setAttribute("genreID", selectedGenreId);
        result = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
        return result;
    }
}
