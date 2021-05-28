package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductDetailKey implements Serializable {

    @Column(name = "pk_fk_productId")
    private int productId;

    @Column(name = "pk_fk_detailLabelId")
    private int detailLabelId;

    public ProductDetailKey() {
    }

    public ProductDetailKey(int productId, int detailLabelId) {
        this.productId = productId;
        this.detailLabelId = detailLabelId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

        if (!(object instanceof ProductDetailKey)) {
            return false;
        }

        ProductDetailKey productDetailKey = (ProductDetailKey) object;

        return productDetailKey.getProductId() == this.productId
                && productDetailKey.getDetailLabelId() == this.detailLabelId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.productId;
        result = 31 * result + this.detailLabelId;

        return result;
    }
}
