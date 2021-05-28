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

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof UserTelephoneKey)) {
            return false;
        }

        UserTelephoneKey userTelephoneKey = (UserTelephoneKey) object;

        return userTelephoneKey.getUserId() == this.userId
                && userTelephoneKey.getTelephoneId() == this.telephoneId;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.userId;
        result = 31 * result + this.telephoneId;

        return result;
    }
}
