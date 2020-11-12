package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.StoreModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStoreService {

    void createStore(StoreModel store, int userId) throws InvalidOperationException;
    void createProfileImage(MultipartFile file, int storeId, int userId) throws InvalidOperationException, IOException;
    List<StoreModel> getAllStores();
    List<StoreModel> getStoresByUserId(int userId) throws InvalidOperationException;
    StoreModel getStoreById(int storeId);
    String getProfileImage(int storeId, int userId, String path) throws InvalidOperationException, IOException;
    void updateStore(StoreModel store, int userId) throws InvalidOperationException;
    void deleteStores(List<Integer> ids, int userId);
}
