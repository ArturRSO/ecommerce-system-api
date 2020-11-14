package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.NotificationsEnum;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.services.IAlertService;
import ecommerce.system.api.services.IStoreService;
import ecommerce.system.api.services.IUserService;
import ecommerce.system.api.tools.NotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertService implements IAlertService {

    private final IStoreService storeService;
    private final IUserService userService;
    private final NotificationHandler notificationHandler;

    @Autowired
    public AlertService(IStoreService storeService, IUserService userService, NotificationHandler notificationHandler) {
        this.storeService = storeService;
        this.userService = userService;
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void sendStockAlert(ProductModel product) throws Exception {

        List<UserModel> users = this.userService.getUsersByStoreId(product.getStoreId());
        StoreModel store = this.storeService.getStoreById(product.getStoreId());

        Map<String, String> data = new HashMap<>();

        data.put("[[productName]]", product.getName());
        data.put("[[storeName]]", store.getName());

        for (UserModel user : users) {
            this.notificationHandler.sendEmail(user.getUserId(), user.getEmail(), NotificationsEnum.STOCK_ALERT, data);
        }
    }
}
