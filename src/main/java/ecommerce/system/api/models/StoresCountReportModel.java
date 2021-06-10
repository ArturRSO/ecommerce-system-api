package ecommerce.system.api.models;

import java.util.UUID;

public class StoresCountReportModel {

    private UUID id;
    private int stores;
    private int activeStores;

    public StoresCountReportModel(UUID id, int stores, int activeStore) {
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
