package dao.interfaces;

import java.sql.SQLException;
import java.util.List;


/**
 * @author Francis
 */
public interface IBaseDAO<T> {
    /**
     * @param o The object to insert into table. Note that the id of this object doesn't need to be set.
     * @return The id of the created object.
     * @throws SQLException
     */
    int create(T o) throws SQLException;

    T getById(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T o) throws SQLException;

    void delete(long id) throws SQLException;
}