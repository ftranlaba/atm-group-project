package dao;

import java.util.List;

public interface IBaseDAO<T> {
    void create(T o);

    T getById(int id);

    List<T> getAll();

    void update(T o, int id);

    void delete(int id);
}