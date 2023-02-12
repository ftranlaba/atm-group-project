package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Functional interface to set the values of a PreparedStatement using an entity.
 * Its functional method is {@link #setValues(PreparedStatement, T)}.
 *
 * @param <T> Type of the entity to set the PreparedStatement with.
 * @author Moussa
 */
@FunctionalInterface
public interface PreparedStatementSetter<T> {
    /**
     * Set the values of the PreparedStatement using the entity.
     *
     * @param ps     PreparedStatement to set.
     * @param entity Entity to set the PreparedStatement with.
     * @throws SQLException see {@link PreparedStatement#setObject(int, Object)}
     */
    void setValues(PreparedStatement ps, T entity) throws SQLException;
}