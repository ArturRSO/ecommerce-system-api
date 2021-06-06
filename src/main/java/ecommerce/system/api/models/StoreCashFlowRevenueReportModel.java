package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class StoreCashFlowRevenueReportModel {

    private UUID id;
    private int storeId;
    private double revenue;
    private LocalDateTime timestamp;

    public StoreCashFlowRevenueReportModel(UUID id, int storeId, double revenue, LocalDateTime timestamp) {
        this.id = id;
        this.revenue = revenue;
        this.timestamp = timestamp;
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

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
