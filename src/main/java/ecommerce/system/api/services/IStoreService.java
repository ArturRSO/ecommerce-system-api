package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.Store;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStoreService {

    int createStore(Store store, int userId) throws InvalidOperationException;

    void createProfileImage(MultipartFile file, int storeId) throws IOException, InvalidOperationException;

    List<Store> getAllStores() throws IOException;

    List<Store> getStoresByUserId(int userId) throws IOException;

    Store getStoreById(int storeId) throws IOException;

    Store getStoreByProductId(int productId) throws IOException;

    void updateStore(Store store) throws InvalidOperationException, IOException;

    void deleteStore(int storeId) throws InvalidOperationException, IOException;
}
