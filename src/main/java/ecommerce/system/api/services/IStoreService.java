package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.StoreModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStoreService {

    int createStore(StoreModel store, int userId) throws InvalidOperationException;
    void createProfileImage(MultipartFile file, int storeId) throws IOException, InvalidOperationException;
    List<StoreModel> getAllStores() throws IOException;
    List<StoreModel> getStoresByUserId(int userId) throws IOException;
    StoreModel getStoreById(int storeId) throws IOException;
    StoreModel getStoreByProductId(int productId) throws IOException;
    void updateStore(StoreModel store) throws InvalidOperationException, IOException;
    void deleteStore(int storeId) throws InvalidOperationException, IOException;
}
