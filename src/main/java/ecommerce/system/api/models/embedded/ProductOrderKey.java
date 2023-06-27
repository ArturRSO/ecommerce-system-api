package ecommerce.system.api.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductOrderKey implements Serializable {

    @Column(name = "pk_fk_productId")
    private int productId;

    @Column(name = "pk_fk_orderId")
    private int orderId;

    public ProductOrderKey() {
    }

    public ProductOrderKey(int productId, int orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderid) {
        this.orderId = orderid;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof ProductOrderKey)) {
            return false;
        }

        ProductOrderKey productOrderKey = (ProductOrderKey) object;

        return productOrderKey.getProductId() == this.productId
                && productOrderKey.getOrderId() == this.orderId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.productId;
        result = 31 * result + this.orderId;

        return result;
    }
}
