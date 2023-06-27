package ecommerce.system.api.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity(name = "StoresCountReport")
@Immutable
@Table(name = "vw_storesCountByStatus")
public class StoresCountReport {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "stores")
    private int stores;

    @Column(name = "activeStores")
    private int activeStores;

    public StoresCountReport() {
    }

    public StoresCountReport(UUID id, int stores, int activeStore) {
        this.id = id;
        this.stores = stores;
        this.activeStores = activeStore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
