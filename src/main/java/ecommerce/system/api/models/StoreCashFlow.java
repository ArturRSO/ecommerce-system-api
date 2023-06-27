package ecommerce.system.api.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "StoreCashFlow")
@Table(name = "tb_storeCashFlow")
public class StoreCashFlow {

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

    public StoreCashFlow() {
    }

    public StoreCashFlow(int storeId, int orderId, double value, LocalDateTime timestamp) {
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
