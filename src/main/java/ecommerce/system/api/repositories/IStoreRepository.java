package ecommerce.system.api.repositories;

import ecommerce.system.api.models.StoreModel;

import java.util.List;

public interface IStoreRepository extends IBaseRepository<StoreModel> {

    void createStoreUser(int storeId, int userId);
    List<StoreModel> getStoresByUserId(int userId);
}
