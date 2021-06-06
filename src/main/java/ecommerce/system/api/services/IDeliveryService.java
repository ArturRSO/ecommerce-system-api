package ecommerce.system.api.services;

import ecommerce.system.api.models.DeliveryModel;

import java.util.List;

public interface IDeliveryService {

    int createDelivery(DeliveryModel delivery);
    double getDeliveryPrice();
    List<DeliveryModel> getDeliveriesByOrderId(int orderId);
    void updateDeliveryStatus(int deliveryId, boolean status);
}
