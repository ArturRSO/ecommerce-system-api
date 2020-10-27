package ecommerce.system.api.models;

public class DetailLabelModel {

    private int detailLabelId;
    private String name;

    public DetailLabelModel(int detailLabelId, String name) {
        this.detailLabelId = detailLabelId;
        this.name = name;
    }

    public int getDetailLabelId() {
        return detailLabelId;
    }

    public void setDetailLabelId(int detailLabelId) {
        this.detailLabelId = detailLabelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
