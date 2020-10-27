package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductDetailModel;

import javax.persistence.*;

@Entity(name = "ProductDetailEntity")
@Table(name = "tb_productDetail")
public class ProductDetailEntity {

    @Id
    @Column(name = "pk_productDetailIdd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailId;

    @Column(name = "fk_detailLabelId")
    private int detailLabelId;

    @Column(name = "fk_productId")
    private int productId;

    @Column(name = "description")
    private String description;

    public ProductDetailEntity() {
    }

    public ProductDetailModel toModel() {
        return new ProductDetailModel(this.productDetailId, null, this.description);
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getDetailLabelId() {
        return detailLabelId;
    }

    public void setDetailLabelId(int detailLabelId) {
        this.detailLabelId = detailLabelId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
