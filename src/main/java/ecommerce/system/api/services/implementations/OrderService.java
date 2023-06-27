package ecommerce.system.api.services.implementations;

import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.dto.PaymentDTO;
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
    private final IPaymentService paymentService;
    private final IProductService productService;
    private final IStoreService storeService;
    private final IUserService userService;

    @Autowired
    public OrderService(
            IAlertService alertService,
            ICashFlowRepository cashFlowRepositoy,
            IDeliveryService deliveryService,
            IOrderRepository orderRepository,
            IPaymentService paymentService,
            IProductService productService,
            IStoreService storeService,
            IUserService userService) {
        this.alertService = alertService;
        this.cashFlowRepository = cashFlowRepositoy;
        this.deliveryService = deliveryService;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.productService = productService;
        this.storeService = storeService;
        this.userService = userService;
    }

    @Override
    public int createOrder(Order order) throws Exception {

        Map<Integer, List<Product>> productsByStore = new HashMap<>();
        double totalPrice = 0;

        for (OrderItemDTO item : order.getItens()) {
            Product product = this.productService.getProductById(item.getProductId());

            if (item.getQuantity() > product.getQuantity()) {
                throw new InvalidOperationException("Estoque insuficiente para o produto " + product.getName());
            }

            totalPrice += product.getPrice() * item.getQuantity();

            List<Product> products = productsByStore.get(product.getStoreId());

            if (products == null) {
                totalPrice += this.deliveryService.getDeliveryPrice();
                products = new ArrayList<>();
            }

            product.setOrderQuantity(item.getQuantity());
            products.add(product);
            productsByStore.put(product.getStoreId(), products);
        }

        order.setTotalPrice(totalPrice);
        order.setTotalDiscountPercentage(0); // HARDCODED
        order.setFinalPrice(totalPrice);
        order.setInstallment(1); // HARDCODED
        order.setCreationDate(LocalDateTime.now());
        order.setLastUpdate(null);
        order.setOrderStatusId(OrderStatusEnum.RECEIVED.getId());

        int orderSummaryId = this.orderRepository.createOrderSummary(order);

        User user = this.userService.getUserById(order.getUserId(), true);

        this.alertService.sendOrderAlert(orderSummaryId, OrderStatusEnum.RECEIVED.getName(), user);

        this.createOrdersByStore(productsByStore, orderSummaryId, order.getAddressId());

        return orderSummaryId;
    }

    @Override
    public List<Order> getOrdersByStoreId(int storeId) {

        return this.orderRepository.getOrdersByStoreId(storeId);
    }

    @Override
    public List<Order> getOrdersByProductId(int productId) {

        return this.orderRepository.getOrdersByProductId(productId);
    }

    @Override
    public List<Order> getOrderSummariesByUserId(int userId) {

        return this.orderRepository.getOrderSummariesByUserId(userId);
    }

    @Override
    public Order getOrderById(int orderId) {

        return this.orderRepository.getOrderById(orderId);
    }

    @Override
    public Order getOrderSummaryById(int orderSummaryId) {

        return this.orderRepository.getOrderSummaryById(orderSummaryId);
    }

    @Override
    public void updateOrderStatus(int orderSummaryid, int orderStatusId) throws Exception {

        Order orderSummary = this.orderRepository.getOrderSummaryById(orderSummaryid);

        if (orderSummary == null) {
            throw new InvalidOperationException("Pedido não encontrado!");
        }

        List<Order> orders = this.orderRepository.getOrdersByOrderSummaryId(orderSummaryid);

        if (orders == null) {
            throw new InvalidOperationException(
                    "Pedido com dados comprometidos! Não foi possível efetuar a atualização de status.");
        }

        orderSummary.setOrderStatusId(orderStatusId);
        orderSummary.setLastUpdate(LocalDateTime.now());

        this.orderRepository.updateOrderSummary(orderSummary);

        User user = this.userService.getUserById(orderSummary.getUserId(), true);

        this.alertService.sendOrderAlert(orderSummary.getOrderId(), OrderStatusEnum.getStatusNameById(orderStatusId),
                user);

        for (Order order : orders) {

            order.setOrderStatusId(orderStatusId);
            order.setLastUpdate(LocalDateTime.now());

            this.orderRepository.updateOrder(order);
        }
    }

    @Override
    public void payOrder(int orderSummaryid, PaymentDTO paymentInfo) throws Exception {

        Order orderSummary = this.orderRepository.getOrderSummaryById(orderSummaryid);

        if (orderSummary == null) {
            throw new InvalidOperationException("Pedido não encontrado!");
        }

        if (orderSummary.getTotalPrice() != paymentInfo.getValue()) {
            throw new InvalidOperationException("Valor do pagamento incorreto!");
        }

        List<Order> orders = this.orderRepository.getOrdersByOrderSummaryId(orderSummaryid);

        if (orders == null) {
            throw new InvalidOperationException(
                    "Pedido com dados comprometidos! Não foi possível efetuar o pagamento.");
        }

        if (this.paymentService.pay(paymentInfo.getPaymentMethod(), paymentInfo.getValue())) {

            orderSummary.setOrderStatusId(OrderStatusEnum.PAID.getId());
            orderSummary.setLastUpdate(LocalDateTime.now());

            this.orderRepository.updateOrderSummary(orderSummary);

            User user = this.userService.getUserById(orderSummary.getUserId(), true);

            this.alertService.sendOrderAlert(orderSummary.getOrderId(), OrderStatusEnum.PAID.getName(), user);

            for (Order order : orders) {

                double commission = order.getTotalPrice() * 0.10; // HARDCODED
                double storeProfit = order.getTotalPrice() - commission;

                order.setOrderStatusId(OrderStatusEnum.PAID.getId());
                order.setLastUpdate(LocalDateTime.now());

                this.orderRepository.updateOrder(order);

                this.cashFlowRepository.createStoreCashFlowRecord(order.getStoreId(), order.getOrderId(), storeProfit);
                this.cashFlowRepository.createSystemCashFlowRecord(order.getOrderId(), commission);
            }
        } else {
            throw new InvalidOperationException("Erro inesperado ao efetuar o pagamento!");
        }
    }

    private void createOrdersByStore(Map<Integer, List<Product>> productsByStore, int orderSummaryId,
            int addressId) throws Exception {

        for (Map.Entry<Integer, List<Product>> entry : productsByStore.entrySet()) {

            double totalPrice = this.deliveryService.getDeliveryPrice();

            for (Product product : entry.getValue()) {

                totalPrice += product.getPrice() * product.getOrderQuantity();

                int productQuantity = product.getQuantity() - product.getOrderQuantity();
                product.setQuantity(productQuantity);

                if (productQuantity == 0) {
                    String productName = product.getName();
                    String storeName = this.storeService.getStoreById(product.getStoreId()).getName();
                    List<User> users = this.userService.getUsersByStoreId(product.getStoreId());

                    this.alertService.sendStockAlert(productName, storeName, users);
                }

                this.productService.updateProduct(product, true);
            }

            Order order = new Order();
            order.setOrderSummaryId(orderSummaryId);
            order.setTotalPrice(totalPrice);
            order.setTotalDiscountPercentage(0); // HARDCODED
            order.setFinalPrice(totalPrice);
            order.setCreationDate(LocalDateTime.now());
            order.setLastUpdate(null);
            order.setOrderStatusId(OrderStatusEnum.RECEIVED.getId());

            int orderId = this.orderRepository.createOrder(order, entry.getKey());

            for (Product product : entry.getValue()) {
                this.orderRepository.createProductOrder(product.getProductId(), orderId, product.getOrderQuantity());
            }

            Store store = this.storeService.getStoreById(entry.getKey());

            Delivery delivery = new Delivery();
            delivery.setDeliveryServiceId(1); // HARDCODED
            delivery.setOrderId(orderId);
            delivery.setSenderAddressId(store.getAddressId());
            delivery.setReceiverAddressId(addressId);

            this.deliveryService.createDelivery(delivery);
        }
    }
}
