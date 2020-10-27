package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class ProductModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int productId;

    private ProductTypeModel productType;

    private ProductSubtypeModel productSubtype;

    private int storeId;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imagePath;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonIgnore
    private boolean active;

    private List<ProductDetailModel> details;

    public ProductModel(
            int productId,
            ProductTypeModel productType,
            ProductSubtypeModel productSubtype,
            int storeId,
            String name,
            String imagePath,
            LocalDateTime creationDate,
            LocalDateTime lastUpdate,
            boolean active,
            List<ProductDetailModel> details) {
        this.productId = productId;
        this.productType = productType;
        this.productSubtype = productSubtype;
        this.storeId = storeId;
        this.name = name;
        this.imagePath = imagePath;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.active = active;
        this.details = details;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public List<ProductDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetailModel> details) {
        this.details = details;
    }
}
