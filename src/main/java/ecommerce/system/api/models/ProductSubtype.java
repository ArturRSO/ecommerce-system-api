package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ProductSubtype")
@Table(name = "tb_productSubtype")
public class ProductSubtype {

    @Id
    @Column(name = "pk_productSubtypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productSubtypeId;

    @Column(name = "fk_productTypeId")
    private int productTypeId;

    @Column(name = "name")
    private String name;

    public ProductSubtype() {
    }

    public ProductSubtype(int productSubtypeId, int productTypeId, String name) {
        this.productSubtypeId = productSubtypeId;
        this.name = name;
        this.productTypeId = productTypeId;
    }

    public int getProductSubtypeId() {
        return productSubtypeId;
    }

    public void setProductSubtypeId(int productSubtypeId) {
        this.productSubtypeId = productSubtypeId;
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
