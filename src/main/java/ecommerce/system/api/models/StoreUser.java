package ecommerce.system.api.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ecommerce.system.api.models.embedded.StoreUserKey;

@Entity(name = "StoreUserEntity")
@Table(name = "tb_store_user")
public class StoreUser {

    @EmbeddedId
    private StoreUserKey id;

    public StoreUser() {
    }

    public StoreUser(StoreUserKey id) {
        this.id = id;
    }

    public StoreUserKey getId() {
        return id;
    }

    public void setId(StoreUserKey id) {
        this.id = id;
    }
}
