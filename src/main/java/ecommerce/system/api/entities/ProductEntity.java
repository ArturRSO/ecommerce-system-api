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

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "isNew")
    private boolean isNew;

    @Column(name = "isActive")
    private boolean active;

    public ProductEntity() {
    }

    public ProductEntity(ProductModel product) {
        this.productId = product.getProductId();
        this.productTypeId = product.getProductTypeId();
        this.productSubtypeId = product.getProductSubtypeId();
        this.storeId = product.getStoreId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.creationDate = product.getCreationDate();
        this.lastUpdate = product.getLastUpdate();
        this.isNew = product.isNew();
        this.active = product.isActive();
    }

    public ProductModel toModel() {
        return new ProductModel(
                this.productId,
                this.productTypeId,
                this.productSubtypeId,
                this.storeId,
                this.name,
                this.price,
                this.quantity,
                this.creationDate,
                this.lastUpdate,
                this.isNew,
                this.active);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
