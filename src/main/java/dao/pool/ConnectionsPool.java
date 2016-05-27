package dao.pool;

import dao.pool.Exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class ConnectionsPool {

    private static final Logger LOG = Logger.getLogger(ConnectionsPool.class);
    private static InitialContext initialContext = null;
    private static DataSource dataSource = null;

    private ConnectionsPool() {
    }

    public static class InstanceHolder{
        private static final ConnectionsPool INSTANCE = new ConnectionsPool();
    }

    public static ConnectionsPool getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static void init() {
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/library");
        } catch (NamingException e) {
            LOG.error(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if(connection == null){
                throw new ConnectionPoolException();
            }
        }catch (ConnectionPoolException e){
            LOG.error(e);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return connection;
    }
}
