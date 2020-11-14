package ecommerce.system.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "SystemCashFlowEntity")
@Table(name = "tb_systemCashFlow")
public class SystemCashFlowEntity {

    @Id
    @Column(name = "pk_systemCashFlowId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int systemCashFlowId;

    @Column(name = "fk_orderId")
    private int orderId;

    @Column(name = "value")
    private double value;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public SystemCashFlowEntity() {
    }

    public SystemCashFlowEntity(int orderId, double value, LocalDateTime timestamp) {
        this.orderId = orderId;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getSystemCashFlowId() {
        return systemCashFlowId;
    }

    public void setSystemCashFlowId(int systemCashFlowId) {
        this.systemCashFlowId = systemCashFlowId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
