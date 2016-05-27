package command.impl;

import command.ICommand;
import entity.impl.Book;
import manager.PagesEnum;
import manager.PagesManager;
import org.apache.log4j.Logger;
import service.BooksService;

import javax.servlet.http.HttpServletRequest;
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
        long selectedGenreId = 0;
        String searchString = request.getParameter("search_string");
        String searchOption = request.getParameter("search_option");
        int startPage = 0;
        if(request.getParameter("startPage")!=null) {
            startPage = Integer.parseInt(request.getParameter("startPage"));
            startPage--;
        }
        bookCount = BooksService.getNumBooksFound(searchString,searchOption);
        books = BooksService.findBooks(searchString,searchOption,startPage);
        LOG.info("Find query processed");
        request.setAttribute("books", books);
        request.setAttribute("booksCount", bookCount);
        request.setAttribute("search_option",searchOption);
        result = PagesManager.getPage(PagesEnum.BOOK_CATALOG);
        LOG.info("LOGIN SUCCESS!");
        return result;
    }
}