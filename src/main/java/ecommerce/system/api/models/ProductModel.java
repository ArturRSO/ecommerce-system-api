package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class ProductModel {

    private int productId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int productTypeId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int productSubtypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductTypeModel productType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductSubtypeModel productSubtype;

    private int storeId;

    private String name;

    private double price;

    private int quantity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonIgnore
    private boolean active;

    private boolean isNew;

    @JsonProperty("details")
    private List<ProductDetailModel> details;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ProductImageModel> images;

    @JsonIgnore
    private int orderQuantity;

    public ProductModel() {
    }

    public ProductModel(
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

    public ProductTypeModel getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeModel productType) {
        this.productType = productType;
    }

    public ProductSubtypeModel getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(ProductSubtypeModel productSubtype) {
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

    public List<ProductDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetailModel> details) {
        this.details = details;
    }

    public List<ProductImageModel> getImages() {
        return images;
    }

    public void setImages(List<ProductImageModel> images) {
        this.images = images;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
