package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.StoreUserKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "StoreUserEntity")
@Table(name = "tb_store_user")
public class StoreUserEntity {

    @EmbeddedId
    private StoreUserKey id;

    public StoreUserEntity() {
    }

    public StoreUserEntity(StoreUserKey id) {
        this.id = id;
    }

    public StoreUserKey getId() {
        return id;
    }

    public void setId(StoreUserKey id) {
        this.id = id;
    }
}
