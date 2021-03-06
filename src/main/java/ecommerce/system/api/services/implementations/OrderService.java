package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.OrderStatusEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.*;
import ecommerce.system.api.repositories.ICashFlowRepository;
import ecommerce.system.api.repositories.IOrderRepository;
import ecommerce.system.api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService implements IOrderService {

    private final IAlertService alertService;
    private final ICashFlowRepository cashFlowRepository;
    private final IDeliveryService deliveryService;
    private final IOrderRepository orderRepository;
    private final IProductService productService;
    private final IStoreService storeService;
    private final IUserService userService;

    @Autowired
    public OrderService(IAlertService alertService, ICashFlowRepository cashFlowRepositoy, IDeliveryService deliveryService, IOrderRepository orderRepository, IProductService productService, IStoreService storeService, IUserService userService) {
        this.alertService = alertService;
        this.cashFlowRepository = cashFlowRepositoy;
        this.deliveryService = deliveryService;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.storeService = storeService;
        this.userService = userService;
    }

    private void createOrdersByStore(Map<Integer, List<ProductModel>> productsByStore, int orderSummaryId, int addressId) throws Exception {

        for (Map.Entry<Integer, List<ProductModel>> entry : productsByStore.entrySet()) {

            double totalPrice = this.deliveryService.getDeliveryPrice();

            for (ProductModel product : entry.getValue()) {

                totalPrice += product.getPrice();

                int productQuantity = product.getQuantity() - 1; // product.getOrderQuantity(); // TODO
                product.setQuantity(productQuantity);

                if (productQuantity == 0) {
                    String productName = product.getName();
                    String storeName = this.storeService.getStoreById(product.getStoreId()).getName();
                    List<UserModel> users = this.userService.getUsersByStoreId(product.getStoreId());

                    this.alertService.sendStockAlert(productName, storeName, users);
                }

                this.productService.updateProduct(product);
            }

            OrderModel order = new OrderModel();
            order.setOrderId(orderSummaryId);
            order.setTotalPrice(totalPrice);
            order.setTotalDiscountPercentage(0);
            order.setFinalPrice(totalPrice);
            order.setCreationDate(LocalDateTime.now());
            order.setLastUpdate(null);
            order.setOrderStatusId(OrderStatusEnum.PAID.getId());

            int orderId = this.orderRepository.createOrder(order, entry.getKey());

            for (ProductModel product : entry.getValue()) {
                this.orderRepository.createProductOrder(product.getProductId(), orderId, 1); //TODO
            }

            StoreModel store = this.storeService.getStoreById(entry.getKey());

            DeliveryModel delivery = new DeliveryModel();
            delivery.setDeliveryServiceId(1); // Hardcoded
            delivery.setOrderId(orderId);
            delivery.setSenderAddressId(store.getAddressId());
            delivery.setReceiverAddressId(addressId);

            this.deliveryService.createDelivery(delivery);

            double commission = totalPrice * 0.10;
            double storeProfit = totalPrice - commission;

            this.cashFlowRepository.createStoreCashFlowRecord(entry.getKey(), orderId, storeProfit);
            this.cashFlowRepository.createSystemCashFlowRecord(orderId, commission);
        }
    }

    @Override
    public void createOrder(OrderModel order) throws Exception {

        Map<Integer, List<ProductModel>> productsByStore = new HashMap<>();
        double totalPrice = 0;

        for (Map<String, Integer> productData : order.getProducts()) {
            ProductModel product = this.productService.getProductById(productData.get("productId"));

            if (productData.get("quantity") > product.getQuantity()) {
                throw new InvalidOperationException("Estoque insuficiente para o produto " + product.getName());
            }

            totalPrice += product.getPrice() * productData.get("quantity");

            List<ProductModel> products = productsByStore.get(product.getStoreId());

            if (products == null) {
                totalPrice += this.deliveryService.getDeliveryPrice();
                products = new ArrayList<>();
            }

            //product.setOrderQuantity(productData.get("quantity")); // TODO
            products.add(product);
            productsByStore.put(product.getStoreId(), products);
        }

        order.setTotalPrice(totalPrice);
        order.setTotalDiscountPercentage(0);
        order.setFinalPrice(totalPrice);
        order.setInstallment(1);
        order.setCreationDate(LocalDateTime.now());
        order.setLastUpdate(null);
        order.setOrderStatusId(OrderStatusEnum.PAID.getId());

        int orderSummaryId = this.orderRepository.createOrderSummary(order);

        this.createOrdersByStore(productsByStore, orderSummaryId, order.getAddressId());
    }

    @Override
    public List<OrderModel> getOrdersByStoreId(int storeId) {

        return this.orderRepository.getOrdersByStoreId(storeId);
    }

    @Override
    public List<OrderModel> getOrderSummariesByUserId(int userId) {

        return this.orderRepository.getOrderSummariesByUserId(userId);
    }
}
