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

    /**
     * @return A list of all the objects in the table.
     * If an error occurs or the table is empty, an empty list is returned.
     */
    List<T> getAll();

    void update(T o);

    void delete(int id);
}