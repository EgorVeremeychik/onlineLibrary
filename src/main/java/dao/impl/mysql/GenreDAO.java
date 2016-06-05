package dao.impl.mysql;

import dao.IBaseDAO;
import dao.pool.ConnectionsPool;
import entity.impl.Genre;
import manager.QueriesEnum;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EgorVeremeychik on 18.04.2016.
 */
public class GenreDAO implements IBaseDAO<Genre> {

    private static final Logger LOG = Logger.getLogger(GenreDAO.class);
    private static final String READ_ALL = "SELECT * FROM genre";
    private static final String READ_BY_GENRE_ID = "SELECT * FROM genre where id =?";

    private GenreDAO() {
    }

    private static class InstanceHolder {
        private static final GenreDAO INSTANCE = new GenreDAO();
    }

    public static GenreDAO getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public boolean create(Genre entity) {
        return false;
    }

    @Override
    public Genre read(int genreID) {
        Genre result = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionsPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_GENRE_ID)){
            preparedStatement.setInt(1,genreID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = createGenre(resultSet);
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
    public Genre read(Genre entity) {
        return null;
    }

    @Override
    public List<Genre> readAll(int start) {
        List<Genre> result = new ArrayList<>();
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Genre genre = createGenre(resultSet);
                result.add(genre);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<Genre> readAll(String str) {
        return null;
    }

    @Override
    public boolean update(Genre entity) {
        return false;
    }

    @Override
    public boolean delete(Genre entity) {
        return false;
    }

    @Override
    public List<Genre> execute(QueriesEnum query, Object[] params, int stsrt) {
        return null;
    }

    @Override
    public List<Genre> execute(QueriesEnum query, long param, int start) {
        return null;
    }

    @Override
    public int count(QueriesEnum query, Object[] params) {
        return 0;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    private Genre createGenre(ResultSet resultSet) {
        Genre result = null;
        try {
            int genreID = resultSet.getInt("id");
            String name = resultSet.getString("name");
            result = new Genre(genreID, name);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }
}
