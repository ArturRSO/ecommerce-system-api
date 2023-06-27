package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ProductType")
@Table(name = "tb_productType")
public class ProductType {

    @Id
    @Column(name = "pk_productTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    @Column(name = "name")
    private String name;

    public ProductType() {
    }

    public ProductType(int productTypeId, String name) {
        this.productTypeId = productTypeId;
        this.name = name;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
