package service;

import dao.IBaseDAO;
import dao.impl.mysql.GenreDAO;
import entity.impl.Genre;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */

public class GenreService {
    private static final Logger LOG = Logger.getLogger(GenreService.class);
    private static IBaseDAO<Genre> genreDAO = GenreDAO.getInstance();


    public static List<Genre> getAllGenre() {
        List<Genre> result = null;
        result = genreDAO.readAll(0);
        return result;
    }
}