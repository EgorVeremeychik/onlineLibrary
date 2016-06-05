package dao.impl.mysql;

import dao.IBaseDAO;
import dao.exception.DAOException;
import dao.pool.ConnectionsPool;
import entity.impl.Publisher;
import manager.QueriesEnum;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EgorVeremeychik on 24.04.2016.
 */
public class PublisherDAO implements IBaseDAO<Publisher> {


    private static final Logger LOG = Logger.getLogger(PublisherDAO.class);
    private static final String READ_BY_PUBLISHER_ID = "SELECT * FROM publisher where id =?";
    private static final String READ_ALL = "SELECT * FROM publisher";

    private PublisherDAO() {
    }

    private static class InstanceHolder {
        private static final PublisherDAO INSTANCE = new PublisherDAO();
    }

    public static PublisherDAO getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public boolean create(Publisher entity) throws DAOException {
        return false;
    }

    @Override
    public Publisher read(int key) {
        Publisher result = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionsPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_PUBLISHER_ID)){
            preparedStatement.setInt(1,key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = createPublisher(resultSet);
            }
        }catch (SQLException e){
            LOG.error(e);
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOG.error(e);
                }
            }
        }
        return result;
    }

    @Override
    public Publisher read(Publisher entity) throws DAOException {
        return null;
    }

    @Override
    public List<Publisher> readAll(int start) {
        List<Publisher> result = new ArrayList<>();
        try(Connection connection = ConnectionsPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Publisher publisher = createPublisher(resultSet);
                result.add(publisher);
            }
        }catch (SQLException e){
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<Publisher> readAll(String string) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Publisher entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int key) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Publisher entity) throws DAOException {
        return false;
    }

    @Override
    public List<Publisher> execute(QueriesEnum query, Object[] params, int stsrt) {
        return null;
    }

    @Override
    public List<Publisher> execute(QueriesEnum query, long param, int start) {
        return null;
    }

    @Override
    public int count(QueriesEnum query, Object[] params) {
        return 0;
    }

    public Publisher createPublisher(ResultSet resultSet){
        Publisher result = null;
        try {
            int publisherID = resultSet.getInt("id");
            String name = resultSet.getString("name");
            result = new Publisher(publisherID, name);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }
}
