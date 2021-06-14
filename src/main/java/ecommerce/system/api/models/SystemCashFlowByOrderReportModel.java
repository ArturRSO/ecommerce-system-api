package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class SystemCashFlowByOrderReportModel {

    private UUID id;
    private int orderId;
    private int storeId;
    private String storeName;
    private double value;
    private LocalDateTime timestamp;

    public SystemCashFlowByOrderReportModel(UUID id, int orderId, int storeId, String storeName, double value, LocalDateTime timestamp) {
        this.id = id;
        this.orderId = orderId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.value = value;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
