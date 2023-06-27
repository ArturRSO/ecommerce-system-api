package ecommerce.system.api.services;

import ecommerce.system.api.dto.PaymentDTO;
import ecommerce.system.api.models.Order;

import java.util.List;

public interface IOrderService {

    int createOrder(Order order) throws Exception;

    List<Order> getOrdersByStoreId(int storeId);

    List<Order> getOrdersByProductId(int productId);

    List<Order> getOrderSummariesByUserId(int userId);

    Order getOrderById(int orderId);

    Order getOrderSummaryById(int orderSummaryId);

    void updateOrderStatus(int orderSummaryid, int orderStatusId) throws Exception;

    void payOrder(int orderSummaryid, PaymentDTO paymentInfo) throws Exception;
}
