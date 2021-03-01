package ecommerce.system.api.repositories;

public interface ICrudRepository<T> {

    int create(T object);
    T getById(int id);
    boolean update(T object);
    boolean delete(int id);
}
