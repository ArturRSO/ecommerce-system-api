package ecommerce.system.api.repositories;

import ecommerce.system.api.models.StoreModel;

import java.util.List;

public interface IStoreRepository extends ICrudRepository<StoreModel> {

    void createStoreUser(int storeId, int userId);
    List<StoreModel> getAllStores();
    List<StoreModel> getStoresByUserId(int userId);
}
