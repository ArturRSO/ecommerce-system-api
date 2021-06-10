package ecommerce.system.api.entities;

import ecommerce.system.api.models.StoresCountReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "StoresCountReportEntity")
@Immutable
@Table(name = "vw_storesCountByStatus")
public class StoresCountReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "stores")
    private int stores;

    @Column(name = "activeStores")
    private int activeStores;

    public StoresCountReportEntity() {
    }

    public StoresCountReportEntity(UUID id, int stores, int activeStore) {
        this.id = id;
        this.stores = stores;
        this.activeStores = activeStore;
    }

    public StoresCountReportModel toModel() {

        return new StoresCountReportModel(this.id, this.stores, this.activeStores);
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

    public void setActiveStores(int activeStore) {
        this.activeStores = activeStore;
    }
}
