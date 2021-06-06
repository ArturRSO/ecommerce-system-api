package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.enums.OrderStatusEnum;
import ecommerce.system.api.enums.RolesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.OrderModel;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IStoreRepository;
import ecommerce.system.api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreService implements IStoreService {

    private final IAuthenticationService authenticationService;
    private final IFileService fileService;
    private final IOrderService orderService;
    private final IStoreRepository storeRepository;
    private final IUserService userService;

    @Autowired
    public StoreService(
            IAuthenticationService authenticationService,
            IFileService fileService,
            @Lazy IOrderService orderService,
            IStoreRepository storeRepository,
            IUserService userService) {
        this.authenticationService = authenticationService;
        this.fileService = fileService;
        this.orderService = orderService;
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Override
    public int createStore(StoreModel store, int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId)) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        store.setCreationDate(LocalDateTime.now());
        store.setLastUpdate(null);
        store.setActive(true);

       int storeId = this.storeRepository.create(store);

       this.storeRepository.relateStoreAndUser(storeId, userId);

       return storeId;
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

        return this.storeRepository.getAllStores();
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
    public StoreModel getStoreByProductId(int productId) {

        return this.storeRepository.getStoreByProductId(productId);
    }

    @Override
    public void updateStore(StoreModel store) throws InvalidOperationException, IOException {

        StoreModel oldStore = this.getStoreById(store.getStoreId());

        if (oldStore == null) {
            throw new InvalidOperationException("Loja não encontrada!");
        }

        List<Integer> userIds = this.storeRepository.getUserIdsByStoreId(store.getStoreId());

        for (int id : userIds) {

            UserModel user = this.userService.getUserById(id, false);

            if (!this.authenticationService.isLoggedUser(id) || user.getRoleId() != RolesEnum.SYSTEM_ADMIN.getId()) {
                throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
            }
        }

        store.setCreationDate(oldStore.getCreationDate());
        store.setLastUpdate(LocalDateTime.now());
        store.setActive(store.isActive());

        this.storeRepository.update(store);
    }

    @Override
    public void deleteStore(int storeId) throws InvalidOperationException, IOException {

        StoreModel store = this.storeRepository.getById(storeId);

        if (store == null) {
            throw new InvalidOperationException("Loja não encontrada.");
        }

        List<Integer> userIds = this.storeRepository.getUserIdsByStoreId(storeId);

        for (int id : userIds) {

            UserModel user = this.userService.getUserById(id, false);

            if (!this.authenticationService.isLoggedUser(id) || user.getRoleId() != RolesEnum.SYSTEM_ADMIN.getId()) {
                throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
            }
        }

        List<OrderModel> orders = this.orderService.getOrdersByStoreId(storeId);

        for (OrderModel order : orders) {

            if (order.getOrderStatusId() != OrderStatusEnum.FINISHED.getId()) {
                throw new InvalidOperationException("Não é possível desativar uma loja com pedidos em aberto.");
            }
        }

        this.storeRepository.delete(storeId);
    }
}
