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
}
