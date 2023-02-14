package dao;

import dao.util.ConnectionPool;
import dao.util.PreparedStatementSetter;
import dao.util.QueryUtil;
import datamodels.IdInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public abstract class AbstractDAO<T extends IdInfo> implements IBaseDAO<T> {
    protected static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    @Override
    public T getById(int id) {
        String query = QueryUtil.entityByIdQuery(getTableName(), getIdColumnName());
        T output = null;
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No entity found with id " + id);
            }
            output = createEntityFromRow(rs);
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        return output;
    }

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

    @Override
    public void update(T entity) {
        String query = QueryUtil.updateQuery(getTableName(), getColumnNames(), getIdColumnName());
        executeCommand(query, this::setUpdatePreparedStatement, entity);
    }

    /**
     * @return gets Column Names from child class.
     */
    protected abstract List<String> getColumnNames();

    /**
     * @param query                   The query to execute. Can be an INSERT, or UPDATE query.
     * @param preparedStatementSetter The method to set the PreparedStatement. Accepts a PreparedStatement and an entity.
     * @param entity                  The entity to use to set the PreparedStatement.
     * @return The id of the created entity if any. Returns 0 if no id was generated.
     */
    private int executeCommand(String query, PreparedStatementSetter<T> preparedStatementSetter, T entity) {
        int output = 0;
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementSetter.setValues(ps, entity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        return output;
    }

    protected void setUpdatePreparedStatement(PreparedStatement preparedStatement, T entity) throws SQLException {
        setCreatePreparedStatement(preparedStatement, entity);
        preparedStatement.setLong(getColumnNames().size() + 1, entity.getId());
    }

    /**
     * @param ps     PreparedStatement to set values for.
     * @param entity Entity to set values from.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    protected abstract void setCreatePreparedStatement(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public void create(T entity) {
        String tableName = getTableName();
        List<String> columnNames = getColumnNames();
        String query = QueryUtil.createQuery(tableName, columnNames);
        int id = executeCommand(query, this::setCreatePreparedStatement, entity);
        entity.setId(id);
    }

    @Override
    public void delete(int id) {
        String query = QueryUtil.deleteQuery(getTableName(), getIdColumnName());
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
        catch(Exception e){
            LOGGER.error(e);
        }
    }

    @Override
    public List<T> getAll() {
        String tableName = getTableName();
        String query = QueryUtil.selectAllQuery(tableName);
        List<T> entities = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T entity = createEntityFromRow(rs);
                entities.add(entity);
            }
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        return entities;
    }
}