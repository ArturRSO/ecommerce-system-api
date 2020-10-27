package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ProductEntity")
@Table(name = "tb_product")
public class ProductEntity {

    @Id
    @Column(name = "pk_productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "fk_productTypeId")
    private int productTypeId;

    @Column(name = "fk_productSubtypeId")
    private int productSubtypeId;

    @Column(name = "fk_storeId")
    private int storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    private boolean active;

    public ProductEntity() {
    }

    public ProductEntity(ProductModel product) {
        this.productId = product.getProductId();
        this.productTypeId = product.getProductType().getProductTypeId();
        this.productSubtypeId = product.getProductSubtype().getProductSubtypeId();
        this.storeId = product.getStoreId();
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.creationDate = product.getCreationDate();
        this.lastUpdate = product.getLastUpdate();
        this.active = product.isActive();
    }

    public ProductModel toModel() {
        return new ProductModel(
                this.productId,
                null,
                null,
                this.storeId,
                this.name,
                this.imagePath,
                this.creationDate,
                this.lastUpdate,
                this.active,
                null
        );
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getProductSubtypeId() {
        return productSubtypeId;
    }

    public void setProductSubtypeId(int productSubtypeId) {
        this.productSubtypeId = productSubtypeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
