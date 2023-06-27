package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ProductDetailLabel")
@Table(name = "tb_detailLabel")
public class ProductDetailLabel {

    @Id
    @Column(name = "pk_detailLabelId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailLabelId;

    @Column(name = "name")
    private String name;

    public ProductDetailLabel() {
    }

    public ProductDetailLabel(int detailLabelId, String name) {
        this.detailLabelId = detailLabelId;
        this.name = name;
    }

    public ProductDetail toProductDetail() {
        return new ProductDetail(this.detailLabelId, this.name);
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
