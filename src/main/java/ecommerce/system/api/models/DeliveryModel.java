package ecommerce.system.api.models;

public class DeliveryModel {

    private int deliveryId;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private double price;
    private boolean success;

    public DeliveryModel(int deliveryId, AddressModel senderAddress, AddressModel receiverAddress, double price, boolean success) {
        this.deliveryId = deliveryId;
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.price = price;
        this.success = success;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
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
}
