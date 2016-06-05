package service;

import dao.IBaseDAO;
import dao.impl.mysql.PublisherDAO;
import entity.impl.Publisher;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by EgorVeremeychik on 05.06.2016.
 */
public class PublisherService {
    private static final Logger LOG = Logger.getLogger(PublisherService.class);
    private static IBaseDAO<Publisher> publisherDAO = PublisherDAO.getInstance();

    public static List<Publisher> getAllPublisher(){
        List<Publisher> result = null;
        result = publisherDAO.readAll(0);
        return result;
    }
}
