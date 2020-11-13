package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductOrderKey implements Serializable  {

    @Column(name = "pk_fk_productId")
    private int productId;

    @Column(name = "pk_fk_orderId")
    private int orderid;

    public ProductOrderKey() {
    }

    public ProductOrderKey(int productId, int orderid) {
        this.productId = productId;
        this.orderid = orderid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
