package ecommerce.system.api.repositories;

import ecommerce.system.api.models.Store;

import java.util.List;

public interface IStoreRepository extends ICrudRepository<Store> {

    void relateStoreAndUser(int storeId, int userId);

    List<Store> getAllStores();

    List<Store> getStoresByUserId(int userId);

    List<Integer> getUserIdsByStoreId(int storeId);

    Store getStoreByProductId(int productId);
}
