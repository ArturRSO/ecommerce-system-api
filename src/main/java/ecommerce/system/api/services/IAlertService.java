package ecommerce.system.api.services;

import ecommerce.system.api.models.User;

import java.util.List;

public interface IAlertService {

    void sendOrderAlert(int orderId, String orderStatus, User user) throws Exception;

    void sendStockAlert(String productName, String storeName, List<User> users) throws Exception;
}
