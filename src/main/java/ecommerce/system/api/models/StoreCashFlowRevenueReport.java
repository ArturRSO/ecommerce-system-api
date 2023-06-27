package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity(name = "StoreCashFlowRevenueReport")
@Immutable
@Table(name = "vw_storeCashFlowRevenue")
public class StoreCashFlowRevenueReport {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "storeId")
    private int storeId;

    @Column(name = "revenue")
    private double revenue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public StoreCashFlowRevenueReport() {
    }

    public StoreCashFlowRevenueReport(UUID id, int storeId, double revenue, LocalDateTime timestamp) {
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
