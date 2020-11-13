package ecommerce.system.api.repositories;

import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderRepository {

    int createOrder(OrderModel order);
    int createOrderSummary(OrderModel order);
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
}
