package ecommerce.system.api.entities.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserTelephoneKey implements Serializable {

    @Column(name = "pk_fk_userId")
    private int userId;

    @Column(name = "pk_fk_telephoneId")
    private int telephoneId;

    public UserTelephoneKey() {
    }

    public UserTelephoneKey(int userId, int telephoneId) {
        this.userId = userId;
        this.telephoneId = telephoneId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(int telephoneId) {
        this.telephoneId = telephoneId;
    }
}
