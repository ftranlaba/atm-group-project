package dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Moussa
 */
public final class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int INITIAL_POOL_SIZE = 5;
    private static final Vector<Connection> FREE_CONNECTIONS = new Vector<>();
    private static final Vector<Connection> USED_CONNECTIONS = new Vector<>();
    private static ConnectionPool instance = null;

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            create();
        }
        return instance;
    }

    public static void create() {
        Properties p = new Properties();

        try (InputStream fileInputStream = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
            p.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        String url = p.getProperty("url");
        String userName = p.getProperty("user");
        String password = p.getProperty("password");

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            FREE_CONNECTIONS.add(createConnection(url, userName, password));
        }
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return new ConnectionWrapper(con);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public synchronized Connection getConnection() {
        Connection connection = FREE_CONNECTIONS.remove(FREE_CONNECTIONS.size() - 1);
        USED_CONNECTIONS.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (USED_CONNECTIONS.remove(connection)) {
            FREE_CONNECTIONS.add(connection);
        } else {
            throw new SQLException("The connection has already returned or it's not for this pool.");
        }
    }
}