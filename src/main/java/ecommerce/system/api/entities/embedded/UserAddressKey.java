package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAddressKey implements Serializable  {

    @Column(name = "pk_fk_userId")
    private int userId;

    @Column(name = "pk_fk_addressId")
    private int addressId;

    public UserAddressKey() {
    }

    public UserAddressKey(int userId, int addressId) {
        this.userId = userId;
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
