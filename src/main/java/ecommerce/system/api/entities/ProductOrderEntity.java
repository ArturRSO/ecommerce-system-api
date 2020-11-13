package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.ProductOrderKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "ProductOrderEntity")
@Table(name = "tb_product_order")
public class ProductOrderEntity {

    @EmbeddedId
    private ProductOrderKey id;

    @Column(name = "quantity")
    private int quantity;

    public ProductOrderEntity() {
    }

    public ProductOrderEntity(ProductOrderKey id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductOrderKey getId() {
        return id;
    }

    public void setId(ProductOrderKey id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
