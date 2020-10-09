package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.BatchUpdateException;

import java.util.List;

public interface IBaseRepository<T> {

    void create(T object);
    List<T> getAll();
    T getById(int id);
    void update(T object);
    void delete(List<Integer> ids) throws BatchUpdateException;
}
