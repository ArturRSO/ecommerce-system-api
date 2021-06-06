package ecommerce.system.api.entities;

import ecommerce.system.api.models.OrdersByStoreReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "OrdersByStoreReportEntity")
@Immutable
@Table(name = "vw_ordersByStore")
public class OrdersByStoreReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "storeId")
    private int storeId;

    @Column(name = "orders")
    private int orders;

    @Column(name = "finishedOrders")
    private int finishedOrders;

    public OrdersByStoreReportEntity() {
    }

    public OrdersByStoreReportEntity(UUID id, int storeId, int orders, int finishedOrders) {
        this.id = id;
        this.storeId = storeId;
        this.orders = orders;
        this.finishedOrders = finishedOrders;
    }

    public OrdersByStoreReportModel toModel() {
        return new OrdersByStoreReportModel(this.id, this.storeId, this.orders, this.finishedOrders);
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

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(int finishedOrders) {
        this.finishedOrders = finishedOrders;
    }
}
