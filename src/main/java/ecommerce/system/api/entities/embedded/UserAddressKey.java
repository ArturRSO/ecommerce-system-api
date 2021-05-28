package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAddressKey implements Serializable {

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

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof UserAddressKey)) {
            return false;
        }

        UserAddressKey userAddressKey = (UserAddressKey) object;

        return userAddressKey.getUserId() == this.userId
                && userAddressKey.getAddressId() == this.addressId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.userId;
        result = 31 * result + this.addressId;

        return result;
    }
}
