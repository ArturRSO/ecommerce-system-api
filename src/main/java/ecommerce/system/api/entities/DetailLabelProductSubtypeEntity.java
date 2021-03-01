package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.DetailLabelProductSubtypeKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DetailLabelProductSubtypeEntity")
@Table(name = "tb_detailLabel_productSubtype")
public class DetailLabelProductSubtypeEntity {

    @EmbeddedId
    private DetailLabelProductSubtypeKey id;

    public DetailLabelProductSubtypeEntity() {
    }

    public DetailLabelProductSubtypeEntity(DetailLabelProductSubtypeKey id) {
        this.id = id;
    }

    public DetailLabelProductSubtypeKey getId() {
        return id;
    }

    public void setId(DetailLabelProductSubtypeKey id) {
        this.id = id;
    }
}
