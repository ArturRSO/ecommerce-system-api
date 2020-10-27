package ecommerce.system.api.entities;

import ecommerce.system.api.models.DetailLabelModel;

import javax.persistence.*;

@Entity(name = "DetailLabelEntity")
@Table(name = "tb_detailLabel")
public class DetailLabelEntity {

    @Id
    @Column(name = "pk_detailLabelId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailLabelId;

    @Column(name = "name")
    private String name;

    public DetailLabelEntity() {
    }

    public DetailLabelModel toModel() {
        return new DetailLabelModel(this.detailLabelId, this.name);
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
