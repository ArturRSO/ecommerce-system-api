package ecommerce.system.api.repositories;

import ecommerce.system.api.models.DeliveryModel;

import java.util.List;

public interface IDeliveryRepository {

    int createDelivery(DeliveryModel delivery);
    List<DeliveryModel> getDeliveriesByOrderId(int orderId);
    boolean updateDeliveryStatus(int deliveryId, boolean status);
}
