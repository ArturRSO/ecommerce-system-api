package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.StoreModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStoreService {

    void createStore(StoreModel store, int userId) throws InvalidOperationException;
    void createProfileImage(MultipartFile file, int storeId) throws IOException, InvalidOperationException;
    List<StoreModel> getAllStores();
    List<StoreModel> getStoresByUserId(int userId);
    StoreModel getStoreById(int storeId);
    StoreModel getStoreByProductId(int productId);
    void updateStore(StoreModel store) throws InvalidOperationException, IOException;
    void deleteStore(int storeId) throws InvalidOperationException, IOException;
}
