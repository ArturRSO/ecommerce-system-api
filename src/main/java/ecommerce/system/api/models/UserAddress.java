package ecommerce.system.api.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ecommerce.system.api.models.embedded.UserAddressKey;

@Entity(name = "UserAddress")
@Table(name = "tb_user_address")
public class UserAddress {

    @EmbeddedId
    private UserAddressKey id;

    public UserAddress() {
    }

    public UserAddress(UserAddressKey id) {
        this.id = id;
    }

    public UserAddressKey getId() {
        return id;
    }

    public void setId(UserAddressKey id) {
        this.id = id;
    }
}
