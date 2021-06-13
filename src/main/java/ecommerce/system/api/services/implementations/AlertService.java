package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.NotificationsEnum;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.services.IAlertService;
import ecommerce.system.api.tools.NotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertService implements IAlertService {

    private final NotificationHandler notificationHandler;

    @Autowired
    public AlertService(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void sendOrderAlert(int orderId, String orderStatus, UserModel user) throws Exception {
        Map<String, String> data = new HashMap<>();

        data.put("[[orderId]]", String.valueOf(orderId));
        data.put("[[orderStatus]]", orderStatus);

        this.notificationHandler.sendEmail(user.getUserId(), user.getEmail(), NotificationsEnum.ORDER_ALERT, data);
    }

    @Override
    public void sendStockAlert(String productName, String storeName, List<UserModel> users) throws Exception {

        Map<String, String> data = new HashMap<>();

        data.put("[[productName]]", productName);
        data.put("[[storeName]]", storeName);

        for (UserModel user : users) {
            this.notificationHandler.sendEmail(user.getUserId(), user.getEmail(), NotificationsEnum.STOCK_ALERT, data);
        }
    }
}
