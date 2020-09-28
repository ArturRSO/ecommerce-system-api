package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;

import java.util.List;

public interface IBaseRepository<T> {

    void create(T object);
    List<T> getAll() throws EmptySearchException;
    T getById(int id);
    void update(T object);
    void delete(List<Integer> ids) throws BatchUpdateException;
}
