package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.models.embedded.ProductOrderKey;

@Entity(name = "ProductOrder")
@Table(name = "tb_product_order")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderKey id;

    @Column(name = "quantity")
    private int quantity;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderKey id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderItemDTO toDTO() {

        return new OrderItemDTO(this.id.getProductId(), this.quantity);
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
