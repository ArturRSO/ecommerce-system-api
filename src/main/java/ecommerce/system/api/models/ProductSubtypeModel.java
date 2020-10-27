package ecommerce.system.api.models;

public class ProductSubtypeModel {

    private int productSubtypeId;
    private int productTypeId;
    private String name;

    public ProductSubtypeModel(int productSubtypeId, int productTypeId, String name) {
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
