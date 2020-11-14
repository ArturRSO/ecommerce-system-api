package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.OrderModel;

import java.util.List;

public interface IOrderService {

    void createOrder(OrderModel order) throws Exception;
    List<OrderModel> getOrdersByStoreId(int storeId);
    List<OrderModel> getOrderSummariesByUserId(int userId);
}
