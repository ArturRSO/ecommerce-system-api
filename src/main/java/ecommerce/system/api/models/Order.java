package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.dto.PaymentMethodDTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Order")
@Table(name = "tb_order")
public class Order {

    @Id
    @Column(name = "pk_orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "fk_orderSummaryId")
    private int orderSummaryId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private int userId;

    @Column(name = "fk_storeId")
    @JsonIgnore
    private int storeId;

    @JsonIgnore
    @Transient
    private int paymentMethodId;

    @Column(name = "totalPrice")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalPrice;

    @Column(name = "totalDiscountPercentage")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalDiscountPercentage;

    @Column(name = "finalPrice")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double finalPrice;

    @Transient
    private int installment;

    @Column(name = "creationDate")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    @JsonIgnore
    private LocalDateTime lastUpdate;

    @Column(name = "fk_orderStatusId")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int orderStatusId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private int addressId;

    @Transient
    private List<OrderItemDTO> itens;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private PaymentMethodDTO paymentMethod;

    public Order() {
    }

    public Order(int orderSummaryIdId, int userId, int paymentMethodId, double totalPrice,
            double totalDiscountPercentage, double finalPrice, int installment, LocalDateTime creationDate,
            LocalDateTime lastUpdate, int orderStatusId) {
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

    public Order(int orderId, int orderSummaryId, int storeId, double totalPrice, double totalDiscountPercentage,
            double finalPrice, LocalDateTime creationDate, LocalDateTime lastUpdate, int orderStatusId) {
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

    public Order(Order order, boolean update) {
        this.orderSummaryId = order.getOrderSummaryId();
        this.storeId = order.getStoreId();
        this.totalPrice = order.getTotalPrice();
        this.totalDiscountPercentage = order.getTotalDiscountPercentage();
        this.finalPrice = order.getFinalPrice();
        this.creationDate = order.getCreationDate();
        this.lastUpdate = order.getLastUpdate();
        this.orderStatusId = order.getOrderStatusId();

        if (update) {
            this.orderId = order.getOrderId();
        }
    }

    public Order(OrderSummary orderSummary) {
        this.orderSummaryId = orderSummary.getOrderSummaryId();
        this.userId = orderSummary.getUserId();
        this.paymentMethodId = orderSummary.getPaymentMethodId();
        this.totalPrice = orderSummary.getTotalPrice();
        this.totalDiscountPercentage = orderSummary.getTotalDiscountPercentage();
        this.finalPrice = orderSummary.getFinalPrice();
        this.installment = orderSummary.getInstallment();
        this.creationDate = orderSummary.getCreationDate();
        this.lastUpdate = orderSummary.getLastUpdate();
        this.orderStatusId = orderSummary.getOrderStatusId();
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

    public List<OrderItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<OrderItemDTO> itens) {
        this.itens = itens;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
