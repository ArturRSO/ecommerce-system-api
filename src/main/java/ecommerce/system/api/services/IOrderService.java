package ecommerce.system.api.services;

import ecommerce.system.api.dto.PaymentDTO;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderService {

    int createOrder(OrderModel order) throws Exception;
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrdersByProductId(int productId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
    void updateOrderStatus(int orderSummaryid, int orderStatusId) throws InvalidOperationException;
    void payOrder(int orderSummaryid, PaymentDTO paymentInfo) throws InvalidOperationException;
}
