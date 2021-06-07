package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int deliveryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int deliveryServiceId;

    private int orderId;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private double price;
    private boolean success;

    @JsonIgnore
    private int senderAddressId;

    @JsonIgnore
    private int receiverAddressId;

    public DeliveryModel() {
    }

    public DeliveryModel(int deliveryId, int deliveryServiceId, int orderId, int senderAddressId, int receiverAddressId, double price, boolean success) {
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

    public AddressModel getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressModel senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressModel getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressModel receiverAddress) {
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
