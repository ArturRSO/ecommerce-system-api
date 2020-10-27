package ecommerce.system.api.entities;

import ecommerce.system.api.models.ProductTypeModel;

import javax.persistence.*;

@Entity(name = "ProductTypeEntity")
@Table(name = "tb_productType")
public class ProductTypeEntity {

    @Id
    @Column(name = "pk_productTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    @Column(name = "name")
    private String name;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(ProductTypeModel productType) {
        this.productTypeId = productType.getProductTypeId();
        this.name = productType.getName();
    }

    public ProductTypeModel toModel() {
        return new ProductTypeModel(this.productTypeId, this.name);
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
