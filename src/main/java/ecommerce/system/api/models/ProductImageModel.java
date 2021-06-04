package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductImageModel {

    private int productImageId;
    private int productId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String path;

    public ProductImageModel(int productImageId, int productId, String path) {
        this.productImageId = productImageId;
        this.productId = productId;
        this.path = path;
    }

    public ProductImageModel(int productId, String path) {
        this.productId = productId;
        this.path = path;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
