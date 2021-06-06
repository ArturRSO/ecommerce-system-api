package ecommerce.system.api.entities;

import ecommerce.system.api.models.SystemCashFlowRevenueReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "SystemCashFlowRevenueReportEntity")
@Immutable
@Table(name = "vw_systemCashFlowRevenue")
public class SystemCashFlowRevenueReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "revenue")
    private double revenue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public SystemCashFlowRevenueReportEntity() {
    }

    public SystemCashFlowRevenueReportEntity(UUID id, double revenue, LocalDateTime timestamp) {
        this.id = id;
        this.revenue = revenue;
        this.timestamp = timestamp;
    }

    public SystemCashFlowRevenueReportModel toModel() {
        return new SystemCashFlowRevenueReportModel(this.id, this.revenue, this.timestamp);
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
