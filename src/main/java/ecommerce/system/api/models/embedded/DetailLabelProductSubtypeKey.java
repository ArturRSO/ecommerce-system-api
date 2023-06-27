package ecommerce.system.api.models.embedded;

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

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof DetailLabelProductSubtypeKey)) {
            return false;
        }

        DetailLabelProductSubtypeKey detailLabelProductSubtypeKey = (DetailLabelProductSubtypeKey) object;

        return detailLabelProductSubtypeKey.getProductSubtypeId() == this.productSubtypeId
                && detailLabelProductSubtypeKey.getDetailLabelId() == this.detailLabelId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.productSubtypeId;
        result = 31 * result + this.detailLabelId;

        return result;
    }
}
