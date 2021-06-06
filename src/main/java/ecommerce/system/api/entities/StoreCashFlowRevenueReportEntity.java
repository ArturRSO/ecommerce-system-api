package ecommerce.system.api.entities;

import ecommerce.system.api.models.StoreCashFlowRevenueReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "StoreCashFlowRevenueReportEntity")
@Immutable
@Table(name = "vw_storeCashFlowRevenue")
public class StoreCashFlowRevenueReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "storeId")
    private int storeId;

    @Column(name = "revenue")
    private double revenue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public StoreCashFlowRevenueReportEntity() {
    }

    public StoreCashFlowRevenueReportEntity(UUID id, int storeId, double revenue, LocalDateTime timestamp) {
        this.id = id;
        this.storeId = storeId;
        this.revenue = revenue;
        this.timestamp = timestamp;
    }

    public StoreCashFlowRevenueReportModel toModel() {
        return new StoreCashFlowRevenueReportModel(this.id, this.storeId, this.revenue, this.timestamp);
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
