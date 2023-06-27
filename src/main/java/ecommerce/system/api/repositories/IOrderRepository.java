package ecommerce.system.api.repositories;

import ecommerce.system.api.models.Order;

import java.util.List;

public interface IOrderRepository {

    int createOrder(Order order, int storeId);

    int createOrderSummary(Order order);

    void createProductOrder(int productId, int orderId, int quantity);

    List<Order> getOrdersByStoreId(int storeId);

    List<Order> getOrdersByProductId(int productId);

    List<Order> getOrderSummariesByUserId(int userId);

    List<Order> getOrdersByOrderSummaryId(int orderSummaryId);

    Order getOrderById(int orderId);

    Order getOrderSummaryById(int orderSummaryId);

    boolean updateOrder(Order order);

    boolean updateOrderSummary(Order orderSummary);
}
