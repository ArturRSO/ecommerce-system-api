package ecommerce.system.api.entities;

import ecommerce.system.api.entities.embedded.RoleUserOptionKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "UserEntity")
@Table(name = "tb_user")
public class RoleUserOptionEntity {

    @EmbeddedId
    private RoleUserOptionKey id;

    public RoleUserOptionEntity() {
    }

    public RoleUserOptionEntity(RoleUserOptionKey id) {
        this.id = id;
    }

    public RoleUserOptionKey getId() {
        return id;
    }

    public void setId(RoleUserOptionKey id) {
        this.id = id;
    }
}
