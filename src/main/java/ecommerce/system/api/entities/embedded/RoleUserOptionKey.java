package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoleUserOptionKey implements Serializable {

    @Column(name = "pk_fk_roleId")
    private int roleId;

    @Column(name = "pk_fk_userOptionId")
    private int userOptionId;

    public RoleUserOptionKey() {
    }

    public RoleUserOptionKey(int roleId, int userOptionId) {
        this.roleId = roleId;
        this.userOptionId = userOptionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserOptionId() {
        return userOptionId;
    }

    public void setUserOptionId(int userOptionId) {
        this.userOptionId = userOptionId;
    }
}
