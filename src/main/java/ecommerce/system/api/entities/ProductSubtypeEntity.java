package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductSubtypeModel;

import javax.persistence.*;

@Entity(name = "ProductSubtypeEntity")
@Table(name = "tb_productSubtype")
public class ProductSubtypeEntity {

    @Id
    @Column(name = "pk_productSubtypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productSubtypeId;

    @Column(name = "fk_productTypeId")
    private int productTypeId;

    @Column(name = "name")
    private String name;

    public ProductSubtypeEntity() {
    }

    public ProductSubtypeEntity(ProductSubtypeModel productSubType) {
        this.productSubtypeId = productSubType.getProductSubtypeId();
        this.productTypeId = productSubType.getProductTypeId();
        this.name = productSubType.getName();
    }

    public ProductSubtypeModel toModel() {
        return new ProductSubtypeModel(this.productSubtypeId, this.productTypeId, this.name);
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
