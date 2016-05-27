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
        int startPage = 0;
        List<Book> books = null;
        long selectedGenreId = 0;
        HttpSession session = request.getSession();
        if(request.getParameter("startPage")!=null) {
            startPage = Integer.parseInt(request.getParameter("startPage"));
            startPage--;
        }
        if (request.getParameter("genreID") != null) {
            selectedGenreId = Long.valueOf(request.getParameter("genreID"));
        } else if (session.getAttribute("genreID") != null) {
            selectedGenreId = Long.valueOf(session.getAttribute("alreadySaw").toString());
        }
        session.setAttribute("alreadySaw", selectedGenreId);
        bookCount = selectedGenreId == 0 ? BooksService.getNumAllBooks() : BooksService.getNumBooksByGenreID(selectedGenreId);
        books = selectedGenreId == 0 ? BooksService.getAllBooks(startPage) : BooksService.getBooksByGenreID(selectedGenreId,startPage);
        request.setAttribute("books",books);
        request.setAttribute("booksCount",bookCount);
        request.setAttribute("startPage", startPage);
        request.setAttribute("pageNo", request.getParameter("pageNo")!=null?request.getParameter("pageNo"):"1");
        if(bookCount%3 == 0){
            bookCount = bookCount - 1;
        }
        request.setAttribute("lastPage", bookCount/3);
        request.setAttribute("genreID", selectedGenreId);
        result = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
        return result;
    }
}
