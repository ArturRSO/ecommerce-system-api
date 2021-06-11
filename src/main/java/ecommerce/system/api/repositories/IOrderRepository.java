package ecommerce.system.api.repositories;

import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderRepository {

    int createOrder(OrderModel order, int storeId);
    int createOrderSummary(OrderModel order);
    void createProductOrder(int productId, int orderId, int quantity);
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrdersByProductId(int productId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
    List<OrderModel> getOrdersByOrderSummaryId(int orderSummaryId);
    OrderModel getOrderById(int orderId);
    OrderModel getOrderSummaryById(int orderSummaryId);
    boolean updateOrder(OrderModel order);
    boolean updateOrderSummary(OrderModel orderSummary);
}
