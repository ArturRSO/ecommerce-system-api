package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DetailLabelProductSubtypeKey implements Serializable {

    @Column(name = "pk_fk_productSubtypeId")
    private int productSubtypeId;

    @Column(name = "pk_fk_detailLabelId")
    private int detailLabelId;

    public DetailLabelProductSubtypeKey() {
    }

    public DetailLabelProductSubtypeKey(int productSubtypeId, int detailLabelId) {
        this.productSubtypeId = productSubtypeId;
        this.detailLabelId = detailLabelId;
    }

    public int getProductSubtypeId() {
        return productSubtypeId;
    }

    public void setProductSubtypeId(int productSubtypeId) {
        this.productSubtypeId = productSubtypeId;
    }

    public int getDetailLabelId() {
        return detailLabelId;
    }

    public void setDetailLabelId(int detailLabelId) {
        this.detailLabelId = detailLabelId;
    }
}
