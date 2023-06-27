package ecommerce.system.api.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "OrderSummary")
@Table(name = "tb_orderSummary")
public class OrderSummary {

    @Id
    @Column(name = "pk_orderSummaryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderSummaryId;

    @Column(name = "fk_userId")
    private int userId;

    @Column(name = "fk_paymentMethodId")
    private int paymentMethodId;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "totalDiscountPercentage")
    private double totalDiscountPercentage;

    @Column(name = "finalPrice")
    private double finalPrice;

    @Column(name = "installment")
    private int installment;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "fk_orderStatusId")
    private int orderStatusId;

    public OrderSummary() {
    }

    public OrderSummary(Order order) {
        this.orderSummaryId = order.getOrderSummaryId();
        this.userId = order.getUserId();
        this.totalPrice = order.getTotalPrice();
        this.totalDiscountPercentage = order.getTotalDiscountPercentage();
        this.finalPrice = order.getFinalPrice();
        this.installment = order.getInstallment();
        this.creationDate = order.getCreationDate();
        this.lastUpdate = order.getLastUpdate();
        this.orderStatusId = order.getOrderStatusId();

        if (order.getPaymentMethod() != null) {
            this.paymentMethodId = order.getPaymentMethod().getPaymentMethodId();

        } else {
            this.paymentMethodId = order.getPaymentMethodId();
        }
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
}
