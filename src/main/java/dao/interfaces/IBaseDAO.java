package dao.interfaces;

import java.sql.SQLException;
import java.util.List;


/**
 * @author Francis
 */
public interface IBaseDAO<T> {
    void create(T o) throws SQLException;

    T getById(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T o) throws SQLException;

    void delete(long id) throws SQLException;
}