package service;

import dao.IBaseDAO;
import dao.exception.DAOException;
import dao.impl.mysql.BookDAO;
import entity.impl.Book;
import manager.QueriesEnum;
import org.apache.log4j.Logger;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by EgorVeremeychik on 18.04.2016.
 */
public class BooksService {

    private static final Logger LOG = Logger.getLogger(BooksService.class);
    private static IBaseDAO<Book> bookDAO = BookDAO.getInstance();

    public static List<Book> getAllBooks(int start){
        List<Book> result = null;
        try{
            result = bookDAO.readAll(start);
            if(result == null){
                throw new ServiceException("Service exception");
            }
        }catch (ServiceException e){
            LOG.error(e.getMessage(),e);
        }
        return result;
    }

    public static List<Book> getBooksByGenreID(long key, int start){
        List<Book> result = null;
        result = bookDAO.execute(QueriesEnum.BOOKS_BY_GENRE_ID, key, start);
        return result;
    }

    public static List<Book> findBooks(String value, String searchOption, int start){
        List<Book> result = null;
        switch (searchOption){
            case "По названию":
                result = bookDAO.execute(QueriesEnum.BOOKS_BY_NAME_OR_AUTHOR,
                        new Object[]{value,null},start);
                break;
            case "По автору":
                result = bookDAO.execute(QueriesEnum.BOOKS_BY_NAME_OR_AUTHOR,
                        new Object[]{null,value},start);
                break;
        }
        return result;
    }

    public static int getNumAllBooks(){
        int result = 0;
        try{
            result = bookDAO.count(QueriesEnum.NUM_ALL_BOOKS,null);
            if(result == 0){
                throw new ServiceException("Service exception");
            }
        }catch (ServiceException e){
            LOG.error(e.getMessage(),e);
        }
        return result;
    }

    public static int getNumBooksByGenreID(long genreID){
        int result = 0;
        result = bookDAO.count(QueriesEnum.NUM_BOOKS_BY_GENRE_ID,new Object[]{genreID});
        return result;
    }

    public static int getNumBooksFound(String searchString, String searchOption){
        int result = 0;
        switch (searchOption){
            case "По названию":
                result = bookDAO.count(QueriesEnum.NUM_BOOKS_FOUND,
                        new Object[]{searchString,null});
                break;
            case "По автору":
                result = bookDAO.count(QueriesEnum.NUM_BOOKS_FOUND,
                        new Object[]{null,searchString});
                break;
        }
        return result;
    }

    public static Book getBookByID(int bookID) {
        Book book;
        book = bookDAO.read(bookID);
        return book;
    }

    public static Boolean addBook(Book book) {
        boolean result = false;
        try {
            result = bookDAO.create(book);
        } catch (DAOException e) {
            LOG.error(e);
        }
        return result;
    }
}
