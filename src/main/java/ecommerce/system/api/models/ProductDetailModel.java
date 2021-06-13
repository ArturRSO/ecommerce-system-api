package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetailModel {

    @JsonProperty("labelId")
    private int labelId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String label;

    @JsonProperty("value")
    private String value;

    public ProductDetailModel() {
    }

    public ProductDetailModel(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public ProductDetailModel(int labelId, String label) {
        this.labelId = labelId;
        this.label = label;
    }

    public ProductDetailModel(int labelId, String label, String value) {
        this.labelId = labelId;
        this.label = label;
        this.value = value;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
