package dao.impl.mysql;

import dao.IBaseDAO;
import dao.exception.DAOException;
import dao.pool.ConnectionsPool;
import entity.impl.Author;
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
public class AuthorDAO implements IBaseDAO<Author> {

    private static final Logger LOG = Logger.getLogger(AuthorDAO.class);
    private static final String READ_BY_AUTHOR_ID = "SELECT * FROM author where id =?";
    private static final String READ_ALL = "SELECT * FROM author";

    private AuthorDAO() {
    }

    private static class InstanceHolder {
        private static final AuthorDAO INSTANCE = new AuthorDAO();
    }

    public static AuthorDAO getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public boolean create(Author entity) throws DAOException {
        return false;
    }

    @Override
    public Author read(Integer key){
        Author result = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionsPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_AUTHOR_ID)){
            preparedStatement.setInt(1,key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = createAuthor(resultSet);
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
    public Author read(Author entity) throws DAOException {
        return null;
    }

    @Override
    public List<Author> readAll(int start) {
        List<Author> result = new ArrayList<>();
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Author author = createAuthor(resultSet);
                result.add(author);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<Author> readAll(String string) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Author entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer key) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Author entity) throws DAOException {
        return false;
    }

    @Override
    public List<Author> execute(QueriesEnum query, Object[] params, int stsrt) {
        return null;
    }

    @Override
    public List<Author> execute(QueriesEnum query, Integer param, int start) {
        return null;
    }

    @Override
    public int count(QueriesEnum query, Object[] params) {
        return 0;
    }

    public Author createAuthor(ResultSet resultSet){
        Author result = null;
        try {
            Integer authorID = resultSet.getInt("id");
            String name = resultSet.getString("fio");
            result = new Author(authorID, name);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }
}
