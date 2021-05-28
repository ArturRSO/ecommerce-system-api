package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoreUserKey implements Serializable {

    @Column(name = "pk_fk_storeId")
    private int storeId;

    @Column(name = "pk_fk_userId")
    private int userId;

    public StoreUserKey() {
    }

    public StoreUserKey(int storeId, int userId) {
        this.storeId = storeId;
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof StoreUserKey)) {
            return false;
        }

        StoreUserKey storeUserKey = (StoreUserKey) object;

        return  storeUserKey.getStoreId() == this.storeId
                && storeUserKey.getUserId() == this.userId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.storeId;
        result = 31 * result + this.userId;

        return result;
    }
}
