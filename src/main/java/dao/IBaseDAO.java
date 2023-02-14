package dao;

import java.util.List;

/**
 * @author Francis
 */
public interface IBaseDAO<T> {
    /**
     * @param o The object to insert into table. Note that the id of this object doesn't need to be set.
     *          This object is modified; its id is set to the id of the created row.
     */
    void create(T o);

    T getById(int id);

    List<T> getAll();

    void update(T o);

    void delete(int id);
}