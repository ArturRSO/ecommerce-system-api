package ecommerce.system.api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity(name = "SystemCashFlowRevenueReport")
@Immutable
@Table(name = "vw_systemCashFlowRevenue")
public class SystemCashFlowRevenueReport {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "revenue")
    private double revenue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public SystemCashFlowRevenueReport() {
    }

    public SystemCashFlowRevenueReport(UUID id, double revenue, LocalDateTime timestamp) {
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