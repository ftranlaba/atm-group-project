package dao;

import dao.interfaces.IBaseDAO;
import datamodels.IdInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public abstract class AbstractDAO<T extends IdInfo> implements IBaseDAO<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    /**
     * @return gets Table Name from child class.
     */
    protected abstract String getTableName();

    /**
     * Creates an entity from a row in the ResultSet.
     * Does not check if the ResultSet is empty.
     *
     * @param rs ResultSet from which to create the entity.
     * @return The entity created from the first if any row of the ResultSet.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    protected abstract T createEntityFromRow(ResultSet rs) throws SQLException;

    /**
     * @return gets ID Column Name from child class.
     */
    protected abstract String getIdColumnName();


    /**
     * @return gets Column Names from child class.
     */
    protected abstract List<String> getColumnNames();

    @Override
    public T getById(long id) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.entityByIdQuery(getTableName(), getIdColumnName());

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No entity found with id " + id);
                }
                return createEntityFromRow(rs);
            }
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        String query = QueryUtil.updateQuery(getTableName(), getColumnNames(), getIdColumnName());
        executeCommand(query, this::setUpdatePreparedStatement, entity);
    }


    /**
     * @param query                   The query to execute. Can be an INSERT, UPDATE or DELETE query.
     * @param preparedStatementSetter The method to set the PreparedStatement. Accepts a PreparedStatement and an entity.
     * @param entity                  The entity to use to set the PreparedStatement.
     */
    private void executeCommand(String query, PreparedStatementSetter<T> preparedStatementSetter, T entity) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            preparedStatementSetter.setValues(ps, entity);
            ps.executeUpdate();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    protected void setUpdatePreparedStatement(PreparedStatement preparedStatement, T entity) throws SQLException {
        setCreatePreparedStatement(preparedStatement, entity);
        preparedStatement.setLong(getColumnNames().size() + 1, entity.id);
    }

    /**
     * @param ps     PreparedStatement to set values for.
     * @param entity Entity to set values from.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    protected abstract void setCreatePreparedStatement(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public void create(T entity) throws SQLException {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = QueryUtil.createQuery(tableName, columnNames);
        executeCommand(query, this::setCreatePreparedStatement, entity);
    }

    @Override
    public void delete(long id) throws SQLException {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.deleteQuery(tableName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        String tableName = getTableName();
        Connection connection = CONNECTION_POOL.getConnection();
        String query = QueryUtil.selectAllQuery(tableName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<T> entities = new ArrayList<>();
                while (rs.next()) {
                    T entity = createEntityFromRow(rs);
                    entities.add(entity);
                }
                return entities;
            }
        } finally {
            try {
                CONNECTION_POOL.releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}