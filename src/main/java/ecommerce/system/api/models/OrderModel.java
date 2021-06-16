package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.dto.PaymentMethodDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderModel {

    private int orderId;

    private int orderSummaryId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;

    @JsonIgnore
    private int storeId;

    @JsonIgnore
    private int paymentMethodId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalPrice;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalDiscountPercentage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double finalPrice;

    private int installment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int orderStatusId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int addressId;

    private List<OrderItemDTO> itens;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PaymentMethodDTO paymentMethod;

    public OrderModel() {
    }

    public OrderModel(int orderSummaryIdId, int userId, int paymentMethodId, double totalPrice, double totalDiscountPercentage, double finalPrice, int installment, LocalDateTime creationDate, LocalDateTime lastUpdate, int orderStatusId) {
        this.orderSummaryId = orderSummaryIdId;
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

    public OrderModel(int orderId, int orderSummaryId, int storeId, double totalPrice, double totalDiscountPercentage, double finalPrice, LocalDateTime creationDate, LocalDateTime lastUpdate, int orderStatusId) {
        this.orderId = orderId;
        this.orderSummaryId = orderSummaryId;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.totalDiscountPercentage = totalDiscountPercentage;
        this.finalPrice = finalPrice;
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

    public int getOrderSummaryId() {
        return orderSummaryId;
    }

    public void setOrderSummaryId(int orderSummaryId) {
        this.orderSummaryId = orderSummaryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public List<OrderItemDTO>  getItens() {
        return itens;
    }

    public void setItens(List<OrderItemDTO>  itens) {
        this.itens = itens;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
