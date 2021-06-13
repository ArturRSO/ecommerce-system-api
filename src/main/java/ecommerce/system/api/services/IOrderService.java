package ecommerce.system.api.services;

import ecommerce.system.api.dto.PaymentDTO;
import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderService {

    int createOrder(OrderModel order) throws Exception;
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrdersByProductId(int productId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
    OrderModel getOrderById(int orderId);
    OrderModel getOrderSummaryById(int orderSummaryId);
    void updateOrderStatus(int orderSummaryid, int orderStatusId) throws Exception;
    void payOrder(int orderSummaryid, PaymentDTO paymentInfo) throws Exception;
}
