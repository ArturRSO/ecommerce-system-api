package ecommerce.system.api.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import ecommerce.system.api.models.embedded.UserTelephoneKey;

@Entity(name = "UserTelephoneEntity")
@Table(name = "tb_user_telephone")
public class UserTelephone {

    @EmbeddedId
    private UserTelephoneKey id;

    public UserTelephone() {
    }

    public UserTelephone(UserTelephoneKey id) {
        this.id = id;
    }

    public UserTelephoneKey getId() {
        return id;
    }

    public void setId(UserTelephoneKey id) {
        this.id = id;
    }
}
