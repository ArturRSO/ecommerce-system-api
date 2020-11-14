package ecommerce.system.api.repositories;

import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderRepository {

    int createOrder(OrderModel order, int storeId);
    int createOrderSummary(OrderModel order);
    void createProductOrder(int productId, int orderId, int quantity);
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
}
