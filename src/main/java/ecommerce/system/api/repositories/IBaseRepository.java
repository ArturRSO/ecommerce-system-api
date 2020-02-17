package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;

import java.util.ArrayList;

public interface IBaseRepository<T> {

    public void create(T object);
    public ArrayList<T> getAll() throws EmptySearchException;
    public T getById(int id);
    public void update(T object);
    public void delete(ArrayList<Integer> ids) throws BatchUpdateException;
}
