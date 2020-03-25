package bsuir.dao.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Optional<T> get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
