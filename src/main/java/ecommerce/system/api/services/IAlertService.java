package ecommerce.system.api.services;

import ecommerce.system.api.models.UserModel;

import java.util.List;

public interface IAlertService {

    void sendOrderAlert(int orderId, String orderStatus, UserModel user) throws Exception;
    void sendStockAlert(String productName, String storeName, List<UserModel> users) throws Exception;
}
