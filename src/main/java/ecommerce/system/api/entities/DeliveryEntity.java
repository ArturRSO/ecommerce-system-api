package ecommerce.system.api.entities;

import ecommerce.system.api.models.DeliveryModel;

import javax.persistence.*;

@Entity(name = "DeliveryEntity")
@Table(name = "tb_delivery")
public class DeliveryEntity {

    @Id
    @Column(name = "pk_deliveryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;

    @Column(name = "fk_deliveryServiceId")
    private int deliveryServiceId;

    @Column(name = "fk_orderId")
    private int orderId;

    @Column(name = "fk_senderAddressId")
    private int senderAddressId;

    @Column(name = "fk_receiverAddressId")
    private int receiverAddressId;

    @Column(name = "price")
    private double price;

    @Column(name = "isSuccess")
    private boolean success;

    public DeliveryEntity() {
    }

    public DeliveryEntity(int deliveryId, int deliveryServiceId, int orderId, int senderAddressId, int receiverAddressId, double price, boolean success) {
        this.deliveryId = deliveryId;
        this.deliveryServiceId = deliveryServiceId;
        this.orderId = orderId;
        this.senderAddressId = senderAddressId;
        this.receiverAddressId = receiverAddressId;
        this.price = price;
        this.success = success;
    }

    public DeliveryModel toModel() {

        return new DeliveryModel(
                this.deliveryId,
                null,
                null,
                this.price,
                this.success
        );
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
}
