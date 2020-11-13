package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderModel {

    private int orderId;
    private int userId;
    private int paymentMethodId;
    private double totalPrice;
    private double totalDiscountPercentage;
    private double finalPrice;
    private int installment;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private int orderStatusId;

    private List<Map<String, Integer>> products;

    public OrderModel(int orderId, int userId, int paymentMethodId, double totalPrice, double totalDiscountPercentage, double finalPrice, int installment, LocalDateTime creationDate, LocalDateTime lastUpdate, int orderStatusId) {
        this.orderId = orderId;
        this.userId = userId;
        this.paymentMethodId = paymentMethodId;
        this.totalPrice = totalPrice;
        this.totalDiscountPercentage = totalDiscountPercentage;
        this.finalPrice = finalPrice;
        this.installment = installment;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.orderStatusId = orderStatusId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscountPercentage() {
        return totalDiscountPercentage;
    }

    public void setTotalDiscountPercentage(double totalDiscountPercentage) {
        this.totalDiscountPercentage = totalDiscountPercentage;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public List<Map<String, Integer>> getProducts() {
        return products;
    }

    public void setProducts(List<Map<String, Integer>> products) {
        this.products = products;
    }
}
