package ecommerce.system.api.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity(name = "StoresByUserReport")
@Immutable
@Table(name = "vw_storesByUserAndStatus")
public class StoresByUserReport {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "stores")
    private int stores;

    @Column(name = "activeStores")
    private int activeStores;

    public StoresByUserReport() {
    }

    public StoresByUserReport(UUID id, int userId, int stores, int activeStores) {
        this.id = id;
        this.userId = userId;
        this.stores = stores;
        this.activeStores = activeStores;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStores() {
        return stores;
    }

    public void setStores(int stores) {
        this.stores = stores;
    }

    public int getActiveStores() {
        return activeStores;
    }

    public void setActiveStores(int activeStores) {
        this.activeStores = activeStores;
    }
}
