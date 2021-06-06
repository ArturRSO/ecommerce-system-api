package ecommerce.system.api.models;

import java.util.UUID;

public class StoresByUserReportModel {

    private UUID id;
    private int userId;
    private int stores;
    private int activeStores;

    public StoresByUserReportModel(UUID id, int userId, int stores, int activeStores) {
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
