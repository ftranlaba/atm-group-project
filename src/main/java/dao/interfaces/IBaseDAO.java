package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Francis
 */
public interface IBaseDAO<T> {
    /**
     * @param o The object to insert into table. Note that the id of this object doesn't need to be set.
     *          This object is modified; its id is set to the id of the created row.
     * @throws SQLException
     */
    void create(T o) throws SQLException;

    T getById(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T o) throws SQLException;

    void delete(long id) throws SQLException;
}