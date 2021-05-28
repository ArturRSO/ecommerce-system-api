package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.UserTelephoneKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "UserTelephoneEntity")
@Table(name = "tb_user_telephone")
public class UserTelephoneEntity {

    @EmbeddedId
    private UserTelephoneKey id;

    public UserTelephoneEntity() {
    }

    public UserTelephoneEntity(UserTelephoneKey id) {
        this.id = id;
    }

    public UserTelephoneKey getId() {
        return id;
    }

    public void setId(UserTelephoneKey id) {
        this.id = id;
    }
}
