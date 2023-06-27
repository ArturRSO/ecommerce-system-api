package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Product")
@Table(name = "tb_product")
public class Product {

    @Id
    @Column(name = "pk_productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "fk_productTypeId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int productTypeId;

    @Column(name = "fk_productSubtypeId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int productSubtypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private ProductType productType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private ProductSubtype productSubtype;

    @Column(name = "fk_storeId")
    private int storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "creationDate")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    @JsonIgnore
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    @JsonIgnore
    private boolean active;

    @Column(name = "isNew")
    private boolean isNew;

    @JsonProperty("details")
    @Transient
    private List<ProductDetail> details;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private List<ProductImage> images;

    @JsonIgnore
    @Transient
    private int orderQuantity;

    public Product() {
    }

    public Product(
            int productId,
            int productTypeId,
            int productSubtypeId,
            int storeId,
            String name,
            double price,
            int quantity,
            LocalDateTime creationDate,
            LocalDateTime lastUpdate,
            boolean active,
            boolean isNew) {
        this.productId = productId;
        this.productTypeId = productTypeId;
        this.productSubtypeId = productSubtypeId;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.isNew = isNew;
        this.active = active;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductSubtype getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(ProductSubtype productSubtype) {
        this.productSubtype = productSubtype;
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

    public List<ProductDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetail> details) {
        this.details = details;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
