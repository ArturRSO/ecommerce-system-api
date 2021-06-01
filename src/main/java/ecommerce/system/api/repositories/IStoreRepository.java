package ecommerce.system.api.repositories;

import ecommerce.system.api.models.StoreModel;

import java.util.List;

public interface IStoreRepository extends ICrudRepository<StoreModel> {

    void relateStoreAndUser(int storeId, int userId);
    List<StoreModel> getAllStores();
    List<StoreModel> getStoresByUserId(int userId);
    List<Integer> getUserIdsByStoreId(int storeId);
    StoreModel getStoreByProductId(int productId);
}
