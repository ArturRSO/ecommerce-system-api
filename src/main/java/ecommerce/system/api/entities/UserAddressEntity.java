package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.UserAddressKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "UserAddressEntity")
@Table(name = "tb_user_address")
public class UserAddressEntity {

    @EmbeddedId
    private UserAddressKey id;

    public UserAddressEntity() {
    }

    public UserAddressEntity(UserAddressKey id) {
        this.id = id;
    }

    public UserAddressKey getId() {
        return id;
    }

    public void setId(UserAddressKey id) {
        this.id = id;
    }
}
