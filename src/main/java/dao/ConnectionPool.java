package dao;

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
    private static ConnectionPool instance = null;
    private static Vector<Connection> freeConnections = new Vector<>();
    private static Vector<Connection> usedConnections = new Vector<>();

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
            freeConnections.add(createConnection(url, userName, password));
        }
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public synchronized Connection getConnection() {
        Connection connection = freeConnections.remove(freeConnections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (usedConnections.remove(connection)) {
            freeConnections.add(connection);
        } else {
            throw new SQLException("The connection has already returned or it's not for this pool.");
        }
    }
}