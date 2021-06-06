package ecommerce.system.api.models;

import java.util.UUID;

public class OrdersByStoreReportModel {

    private UUID id;
    private int storeId;
    private int orders;
    private int finishedOrders;

    public OrdersByStoreReportModel(UUID id, int storeId, int orders, int finishedOrders) {
        this.id = id;
        this.storeId = storeId;
        this.orders = orders;
        this.finishedOrders = finishedOrders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(int finishedOrders) {
        this.finishedOrders = finishedOrders;
    }
}
