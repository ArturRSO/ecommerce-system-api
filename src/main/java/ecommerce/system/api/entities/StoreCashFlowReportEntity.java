package ecommerce.system.api.entities;

import ecommerce.system.api.models.StoreCashFlowReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "StoreCashFlowReportEntity")
@Immutable
@Table(name = "vw_storeCashFlowByProduct")
public class StoreCashFlowReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "storeId")
    private int storeId;

    @Column(name = "orderId")
    private int orderId;

    @Column(name = "value")
    private double value;

    @Column(name = "product")
    private String productName;

    @Column(name = "quantity")
    private int productQuantity;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public StoreCashFlowReportEntity() {
    }

    public StoreCashFlowReportEntity(UUID id, int storeId, int orderId, double value, String productName, int productQuantity, LocalDateTime timestamp) {
        this.id = id;
        this.storeId = storeId;
        this.orderId = orderId;
        this.value = value;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.timestamp = timestamp;
    }

    public StoreCashFlowReportModel toModel() {
        return new StoreCashFlowReportModel(this.id, this.storeId, this.orderId, this.value, this.productName, this.productQuantity, this.timestamp);
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
