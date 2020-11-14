package ecommerce.system.api.services;

import ecommerce.system.api.models.ProductModel;

public interface IAlertService {

    void sendStockAlert(ProductModel product) throws Exception;
}
