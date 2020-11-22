package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.repositories.IStoreRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IFileService;
import ecommerce.system.api.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreService implements IStoreService {

    private final IAuthenticationService authenticationService;
    private final IFileService fileService;
    private final IStoreRepository storeRepository;

    @Autowired
    public StoreService(IAuthenticationService authenticationService, IFileService fileService, IStoreRepository storeRepository) {
        this.authenticationService = authenticationService;
        this.fileService = fileService;
        this.storeRepository = storeRepository;
    }

    @Override
    public void createStore(StoreModel store, int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId)) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        store.setCreationDate(LocalDateTime.now());
        store.setLastUpdate(null);
        store.setActive(true);

       int storeId = this.storeRepository.create(store);

       this.storeRepository.createStoreUser(storeId, userId);
    }

    @Override
    public void createProfileImage(MultipartFile file, int storeId) throws InvalidOperationException, IOException {
        StoreModel store = this.storeRepository.getById(storeId);

        if (store == null) {
            throw new InvalidOperationException("Loja não encontrada!");
        }

        String imagePath = this.fileService.saveMultpartImage(file, "store", storeId);

        store.setProfileImagePath(imagePath);
        store.setLastUpdate(LocalDateTime.now());

        this.storeRepository.update(store);
    }

    @Override
    public List<StoreModel> getAllStores() {

        return this.storeRepository.getAll();
    }

    @Override
    public List<StoreModel> getStoresByUserId(int userId) {

        return this.storeRepository.getStoresByUserId(userId);
    }

    @Override
    public StoreModel getStoreById(int storeId) {

        return this.storeRepository.getById(storeId);
    }

    @Override
    public String getProfileImage(String path) throws IOException {

        return this.fileService.getImageBase64(UriUtils.decode(path, "UTF-8"));
    }

    @Override
    public void updateStore(StoreModel store) throws InvalidOperationException {

        StoreModel oldStore = this.getStoreById(store.getStoreId());

        if (oldStore == null) {
            throw new InvalidOperationException("Loja não encontrada!");
        }

        store.setCreationDate(oldStore.getCreationDate());
        store.setLastUpdate(LocalDateTime.now());
        store.setActive(store.isActive());

        this.storeRepository.update(store);
    }

    @Override
    public void deleteStore(int storeId) {

        // TODO
    }
}
