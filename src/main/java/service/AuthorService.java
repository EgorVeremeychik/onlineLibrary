package service;

import dao.IBaseDAO;
import dao.impl.mysql.AuthorDAO;
import entity.impl.Author;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by EgorVeremeychik on 05.06.2016.
 */
public class AuthorService {
    private static final Logger LOG = Logger.getLogger(AuthorService.class);
    private static IBaseDAO<Author> authorDAO = AuthorDAO.getInstance();

    public static List<Author> getAllAuthor() {
        List<Author> result = null;
        result = authorDAO.readAll(0);
        return result;
    }
}
