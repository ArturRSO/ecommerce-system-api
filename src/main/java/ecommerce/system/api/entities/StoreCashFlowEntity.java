package ecommerce.system.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "StoreCashFlowEntity")
@Table(name = "tb_storeCashFlow")
public class StoreCashFlowEntity {

    @Id
    @Column(name = "pk_storeCashFlowId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeCashFlowId;

    @Column(name = "fk_storeId")
    private int storeId;

    @Column(name = "fk_orderId")
    private int orderId;

    @Column(name = "value")
    private double value;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public StoreCashFlowEntity() {
    }

    public StoreCashFlowEntity(int storeId, int orderId, double value, LocalDateTime timestamp) {
        this.storeId = storeId;
        this.orderId = orderId;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getStoreCashFlowId() {
        return storeCashFlowId;
    }

    public void setStoreCashFlowId(int storeCashFlowId) {
        this.storeCashFlowId = storeCashFlowId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
