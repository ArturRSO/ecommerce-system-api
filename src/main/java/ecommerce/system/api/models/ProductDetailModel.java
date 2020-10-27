package ecommerce.system.api.models;

public class ProductDetailModel {

    private int productDetailId;
    private DetailLabelModel label;
    private String description;

    public ProductDetailModel(int productDetailId, DetailLabelModel label, String description) {
        this.productDetailId = productDetailId;
        this.label = label;
        this.description = description;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public DetailLabelModel getLabel() {
        return label;
    }

    public void setLabel(DetailLabelModel label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
