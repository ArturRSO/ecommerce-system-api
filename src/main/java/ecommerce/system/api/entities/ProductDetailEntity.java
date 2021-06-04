package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.ProductDetailKey;
import ecommerce.system.api.models.ProductDetailModel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ProductDetailEntity")
@Table(name = "tb_productDetail")
public class ProductDetailEntity {

    @EmbeddedId
    private ProductDetailKey id;

    @Column(name = "value")
    private String value;

    public ProductDetailEntity() {
    }

    public ProductDetailEntity(ProductDetailKey id, String value) {
        this.id = id;
        this.value = value;
    }

    public ProductDetailEntity(ProductDetailModel detail, int productId) {
        this.id = new ProductDetailKey(productId, detail.getLabelId());
        this.value = detail.getValue();
    }

    public ProductDetailKey getId() {
        return id;
    }

    public void setId(ProductDetailKey id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
