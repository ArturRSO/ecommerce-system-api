package ecommerce.system.api.services;

import ecommerce.system.api.models.UserModel;

import java.util.List;

public interface IAlertService {

    void sendStockAlert(String productName, String storeName, List<UserModel> users) throws Exception;
}
