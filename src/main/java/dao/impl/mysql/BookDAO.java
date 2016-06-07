package dao.impl.mysql;

import dao.IBaseDAO;
import dao.exception.DAOException;
import dao.pool.ConnectionsPool;
import entity.impl.Author;
import entity.impl.Book;
import entity.impl.Genre;
import entity.impl.Publisher;
import manager.QueriesEnum;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */
public class BookDAO implements IBaseDAO<Book> {

    private static final Logger LOG = Logger.getLogger(BookDAO.class);
    public static final String SELECT_BOOK_BY_NAME_OR_AUTHOR =
            "SELECT * FROM book INNER JOIN author " +
                    "ON book.author_id = author.id WHERE book.name LIKE ? OR author.fio LIKE ? LIMIT 6 OFFSET ?";
    private static final String CREATE =
            "INSERT INTO book(name, content, page_count, isbn, genre_id, " +
                    "author_id, publish_year, publisher_id, image, descr) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String READ_ALL = "SELECT * FROM book LIMIT 6 OFFSET ?";
    private static final String SELECT_BOOK_BY_GENRE_ID = "SELECT * FROM book WHERE genre_id = ? LIMIT 6 OFFSET ?";
    private static final String NUM_ALL_BOOKS = "SELECT COUNT(*) FROM book";
    private static final String NUM_BOOKS_BY_GENRE_ID = "SELECT COUNT(*) FROM book WHERE genre_id = ?";
    private static final String NUM_BOOKS_FOUND = "SELECT COUNT(*) FROM book INNER JOIN author " +
            "ON book.author_id = author.id WHERE book.name LIKE ? OR author.fio LIKE ?";
    private static final String READ_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";

    private BookDAO() {
    }

    private static class InstanceHolder {
        private static final BookDAO INSTANCE = new BookDAO();
    }

    public static BookDAO getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public boolean create(Book entity) throws DAOException {
        boolean result = false;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBytes(2, entity.getContent());
            preparedStatement.setInt(3, entity.getPageCount());
            preparedStatement.setString(4, entity.getIsbn());
            preparedStatement.setInt(5, entity.getGenre().getId());
            preparedStatement.setInt(6, entity.getAuthor().getId());
            preparedStatement.setInt(7, entity.getPublishDate());
            preparedStatement.setInt(8, entity.getPublisher().getId());
            preparedStatement.setString(9, entity.getImage());
            preparedStatement.setString(10, entity.getDescription());
            preparedStatement.executeQuery();
            result = true;
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public Book read(Integer bookID) {
        Book result = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BOOK_BY_ID)) {
            preparedStatement.setInt(1, bookID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = createBook(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
        return result;
    }

    @Override
    public Book read(Book entity) {
        return null;
    }

    @Override
    public List<Book> readAll(int start) {
        List<Book> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
            preparedStatement.setInt(1, start * 6);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                result.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
        return result;
    }

    @Override
    public List<Book> readAll(String string) {
        return null;
    }

    @Override
    public boolean update(Book entity) {
        return false;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Book entity) {
        return false;
    }

    @Override
    public List<Book> execute(QueriesEnum query, Object[] params, int start) {
        List<Book> result = null;
        switch (query) {
            case BOOKS_BY_NAME_OR_AUTHOR:
                result = readBookByNameOrAuthor((String) params[0], (String) params[1], start);
                break;
        }
        return result;
    }

    @Override
    public List<Book> execute(QueriesEnum query, Integer param, int start) {
        List<Book> result = null;
        switch (query) {
            case BOOKS_BY_GENRE_ID:
                result = readBookByGenreID(param, start);
                break;
        }
        return result;
    }

    @Override
    public int count(QueriesEnum query, Object[] params) {
        int result = 0;
        switch (query) {
            case NUM_ALL_BOOKS:
                result = getNumAllBooks();
                break;
            case NUM_BOOKS_BY_GENRE_ID:
                result = getNumBooksByGenreID((Integer) params[0]);
                break;
            case NUM_BOOKS_FOUND:
                result = getNumBooksFound((String) params[0], (String) params[1]);
                break;
        }
        return result;
    }

    public Book createBook(ResultSet resultSet) {
        Book result = null;
        try {
            Integer bookID = resultSet.getInt("id");
            Integer genreID = resultSet.getInt("genre_id");
            Genre genre = GenreDAO.getInstance().read(genreID);
            Integer authorID = resultSet.getInt("author_id");
            Author author = AuthorDAO.getInstance().read(authorID);
            String name = resultSet.getString("name");
            byte[] content = resultSet.getBytes("content");
            int pageCount = resultSet.getInt("page_count");
            String isbn = resultSet.getString("isbn");
            int publishDate = resultSet.getInt("publish_year");
            Integer publisherID = resultSet.getInt("publisher_id");
            Publisher publisher = PublisherDAO.getInstance().read(publisherID);
            String image = resultSet.getString("image");
            String description = resultSet.getString("descr");
            result = new Book(bookID, name, pageCount, isbn, genre, publishDate, author, publisher, image, description, content);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    public List<Book> readBookByNameOrAuthor(String bookName, String authorFio, int start) {
        List<Book> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_NAME_OR_AUTHOR)) {
            preparedStatement.setString(1, "%" + bookName + "%");
            preparedStatement.setString(2, "%" + authorFio + "%");
            preparedStatement.setInt(3, start * 6);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                result.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private List<Book> readBookByGenreID(Integer genreID, int start) {
        List<Book> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_GENRE_ID)) {
            preparedStatement.setInt(1, genreID);
            preparedStatement.setInt(2, start * 6);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                result.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int getNumAllBooks() {
        int result = 0;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NUM_ALL_BOOKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    private int getNumBooksByGenreID(Integer genreID) {
        int result = 0;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NUM_BOOKS_BY_GENRE_ID)) {
            preparedStatement.setInt(1, genreID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    private int getNumBooksFound(String searchString, String searchOption) {
        int result = 0;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionsPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NUM_BOOKS_FOUND)) {
            preparedStatement.setString(1, "%" + searchString + "%");
            preparedStatement.setString(2, "%" + searchOption + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return result;
    }

    public Set<Book> readBookByCategory(Integer categoryId, Integer start) {
        return null;
    }

    public Set<Book> readMostPopular() {
        return null;
    }

    public Set<Book> readNewest() {
        return null;
    }

    public boolean updatePath(String path, Integer bookId) {
        return false;
    }

}
