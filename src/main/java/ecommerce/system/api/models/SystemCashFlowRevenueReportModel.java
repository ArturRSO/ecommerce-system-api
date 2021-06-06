package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class SystemCashFlowRevenueReportModel {

    private UUID id;
    private double revenue;
    private LocalDateTime timestamp;

    public SystemCashFlowRevenueReportModel(UUID id, double revenue, LocalDateTime timestamp) {
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