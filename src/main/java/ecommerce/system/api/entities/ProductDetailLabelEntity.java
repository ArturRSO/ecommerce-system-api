package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductDetailModel;

import javax.persistence.*;

@Entity(name = "ProductDetailLabelEntity")
@Table(name = "tb_detailLabel")
public class ProductDetailLabelEntity {

    @Id
    @Column(name = "pk_detailLabelId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailLabelId;

    @Column(name = "name")
    private String name;

    public ProductDetailLabelEntity() {
    }

    public ProductDetailLabelEntity(int detailLabelId, String name) {
        this.detailLabelId = detailLabelId;
        this.name = name;
    }

    public ProductDetailModel toModel() {

        return new ProductDetailModel(this.detailLabelId, this.name);
    }

    public int getDetailLabelId() {
        return detailLabelId;
    }

    public void setDetailLabelId(int detailLabelId) {
        this.detailLabelId = detailLabelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
