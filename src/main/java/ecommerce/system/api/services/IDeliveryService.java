package ecommerce.system.api.services;

import ecommerce.system.api.models.Delivery;

import java.util.List;

public interface IDeliveryService {

    int createDelivery(Delivery delivery);

    double getDeliveryPrice();

    List<Delivery> getDeliveriesByOrderId(int orderId);

    void updateDeliveryStatus(int deliveryId, boolean status);
}
