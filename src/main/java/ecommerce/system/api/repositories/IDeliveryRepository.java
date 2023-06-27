package ecommerce.system.api.repositories;

import ecommerce.system.api.models.Delivery;

import java.util.List;

public interface IDeliveryRepository {

    int createDelivery(Delivery delivery);

    List<Delivery> getDeliveriesByOrderId(int orderId);

    List<Delivery> getDeliveriesByOrderSummaryId(int orderSummaryId);

    boolean updateDeliveryStatus(int deliveryId, boolean status);
}
