package ecommerce.system.api.models;

import java.util.UUID;

public class ProductsByStoreReportModel {

    private UUID id;
    private String storeName;
    private int storeId;
    private int products;
    private int activeProducts;

    public ProductsByStoreReportModel(UUID id, int storeId, String storeName, int products, int activeProducts) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.products = products;
        this.activeProducts = activeProducts;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public int getActiveProducts() {
        return activeProducts;
    }

    public void setActiveProducts(int activeProducts) {
        this.activeProducts = activeProducts;
    }
}
