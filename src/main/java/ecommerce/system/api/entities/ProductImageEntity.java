package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductImageModel;

import javax.persistence.*;

@Entity(name = "ProductImageEntity")
@Table(name = "tb_productImage")
public class ProductImageEntity {

    @Id
    @Column(name = "pk_productImageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productImageId;

    @Column(name = "fk_productId")
    private int productId;

    @Column(name = "path")
    private String path;

    public ProductImageEntity() {
    }

    public ProductImageEntity(int productId, String path) {
        this.productId = productId;
        this.path = path;
    }

    public ProductImageEntity(ProductImageModel image) {
        this.productImageId = image.getProductImageId();
        this.productId = image.getProductId();
        this.path = image.getPath();
    }

    public ProductImageModel toModel() {

        return new ProductImageModel(this.productImageId, this.productId, this.path);
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
