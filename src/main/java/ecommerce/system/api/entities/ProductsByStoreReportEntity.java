package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductsByStoreReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "ProductsByStoreReportEntity")
@Immutable
@Table(name = "vw_productsByStoreAndStatus")
public class ProductsByStoreReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "storeId")
    private int storeId;

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "products")
    private int products;

    @Column(name = "activeProducts")
    private int activeProducts;

    public ProductsByStoreReportEntity() {
    }

    public ProductsByStoreReportEntity(UUID id, int storeId, String storeName, int products, int activeProducts) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.products = products;
        this.activeProducts = activeProducts;
    }

    public ProductsByStoreReportModel toModel() {
        return new ProductsByStoreReportModel(this.id, this.storeId, this.storeName, this.products, this.activeProducts);
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
