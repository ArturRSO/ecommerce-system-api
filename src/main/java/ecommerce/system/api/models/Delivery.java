package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "Delivery")
@Table(name = "tb_delivery")
public class Delivery {

    @Id
    @Column(name = "pk_deliveryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int deliveryId;

    @Column(name = "fk_deliveryServiceId")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int deliveryServiceId;

    @Column(name = "fk_orderId")
    private int orderId;

    @JsonIgnore
    @Column(name = "fk_senderAddressId")
    private int senderAddressId;

    @JsonIgnore
    @Column(name = "fk_receiverAddressId")
    private int receiverAddressId;

    @Column(name = "price")
    private double price;

    @Column(name = "isSuccess")
    private boolean success;

    @Transient
    private Address senderAddress;

    @Transient
    private Address receiverAddress;

    public Delivery() {
    }

    public Delivery(int deliveryId, int deliveryServiceId, int orderId, int senderAddressId, int receiverAddressId,
            double price, boolean success) {
        this.deliveryId = deliveryId;
        this.deliveryServiceId = deliveryServiceId;
        this.orderId = orderId;
        this.senderAddressId = senderAddressId;
        this.receiverAddressId = receiverAddressId;
        this.price = price;
        this.success = success;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliveryServiceId() {
        return deliveryServiceId;
    }

    public void setDeliveryServiceId(int deliveryServiceId) {
        this.deliveryServiceId = deliveryServiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Address getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Address getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(Address receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(int senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public int getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(int receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }
}
