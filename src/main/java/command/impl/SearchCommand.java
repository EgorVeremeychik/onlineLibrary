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
 * Created by EgorVeremeychik on 01.05.2016.
 */
public class SearchCommand implements ICommand {
    private static final Logger LOG = Logger.getLogger(SearchCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String result;
        int bookCount = 0;
        List<Book> books = null;
        HttpSession session = request.getSession();
        session.setAttribute("alreadySaw", null);
        int page = Integer.parseInt(request.getParameter("page"));
        String searchString = request.getParameter("search_string");
        String searchOption = request.getParameter("search_option");
        int startPage = page - 1;
        bookCount = BooksService.getNumBooksFound(searchString,searchOption);
        books = BooksService.findBooks(searchString,searchOption,startPage);
        LOG.info("Find query processed");
        request.setAttribute("books", books);
        request.setAttribute("startPage", startPage);
        request.setAttribute("page", page);
        request.setAttribute("booksCount", bookCount);
        request.setAttribute("search_option",searchOption);
        request.setAttribute("search_string",searchString);
        bookCount = bookCount%3 == 0 ? (bookCount - 1) : bookCount;
        request.setAttribute("lastPage", bookCount/3);
        result = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
        LOG.info("LOGIN SUCCESS!");
        return result;
    }
}