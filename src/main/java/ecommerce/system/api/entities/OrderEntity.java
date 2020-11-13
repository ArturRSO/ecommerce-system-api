package ecommerce.system.api.entities;

import ecommerce.system.api.models.OrderModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "OrderEntity")
@Table(name = "tb_order")
public class OrderEntity {

    @Id
    @Column(name = "pk_orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "fk_orderSummaryId")
    private int orderSummaryId;

    @Column(name = "fk_storeId")
    private int storeId;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "totalDiscountPercentage")
    private double totalDiscountPercentage;

    @Column(name = "finalPrice")
    private double finalPrice;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "orderStatusId")
    private int orderStatusId;

    public OrderEntity() {
    }

    public OrderEntity(OrderModel order) {
        this.orderSummaryId = order.getOrderId();
        this.totalPrice = order.getTotalPrice();
        this.totalDiscountPercentage = order.getTotalDiscountPercentage();
        this.finalPrice = order.getFinalPrice();
        this.creationDate = order.getCreationDate();
        this.lastUpdate = order.getLastUpdate();
        this.orderStatusId = order.getOrderStatusId();
    }

    public OrderModel toModel() {
        return new OrderModel(
                this.orderId,
                0,
                0,
                this.totalPrice,
                this.totalDiscountPercentage,
                this.finalPrice,
                0,
                this.creationDate,
                this.lastUpdate,
                this.orderStatusId
        );
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
}
