package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import ecommerce.system.api.models.embedded.ProductDetailKey;

@Entity(name = "ProductDetail")
@Table(name = "tb_productDetail")
public class ProductDetail {

    @EmbeddedId
    private ProductDetailKey id;

    @JsonProperty("labelId")
    @Transient
    private int labelId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Transient
    private String label;

    @Column(name = "value")
    @JsonProperty("value")
    private String value;

    public ProductDetail() {
    }

    public ProductDetail(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public ProductDetail(int labelId, String label) {
        this.labelId = labelId;
        this.label = label;
    }

    public ProductDetail(int labelId, String label, String value) {
        this.labelId = labelId;
        this.label = label;
        this.value = value;
    }

    public ProductDetail(ProductDetailKey id, String value) {
        this.id = id;
        this.value = value;
    }

    public ProductDetail(ProductDetail detail, int productId) {
        this.id = new ProductDetailKey(productId, detail.getLabelId());
        this.value = detail.getValue();
    }

    public ProductDetailKey getId() {
        return id;
    }

    public void setId(ProductDetailKey id) {
        this.id = id;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
