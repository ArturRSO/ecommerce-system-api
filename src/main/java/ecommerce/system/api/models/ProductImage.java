package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "ProductImage")
@Table(name = "tb_productImage")
public class ProductImage {

    @Id
    @Column(name = "pk_productImageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productImageId;

    @Column(name = "fk_productId")
    private int productId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private String image;

    @Column(name = "path")
    @JsonIgnore
    private String path;

    public ProductImage() {
    }

    public ProductImage(int productImageId, int productId, String path) {
        this.productImageId = productImageId;
        this.productId = productId;
        this.path = path;
    }

    public ProductImage(int productId, String path) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
