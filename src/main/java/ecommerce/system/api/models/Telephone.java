package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "Telephone")
@Table(name = "tb_telephone")
public class Telephone {

    @Id
    @Column(name = "pk_telephoneId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int telephoneId;

    @NotNull
    private int userId;

    @Column(name = "fk_telephoneTypeId")
    @NotNull
    private int telephoneTypeId;

    @Column(name = "internationalCode")
    @NotNull
    @Size(min = 2, max = 5)
    private String internationalCode;

    @Column(name = "localCode")
    @NotNull
    private int localCode;

    @Column(name = "number")
    @NotNull
    @Size(min = 8, max = 9)
    private String number;

    @Column(name = "creationDate")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    @JsonIgnore
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    @JsonIgnore
    private boolean active;

    public Telephone() {
    }

    public Telephone(int telephoneId,
            int telephoneTypeId,
            String internationalCode,
            int localCode,
            String number,
            LocalDateTime creationDate,
            LocalDateTime lastUpdate,
            boolean active) {
        this.telephoneId = telephoneId;
        this.telephoneTypeId = telephoneTypeId;
        this.internationalCode = internationalCode;
        this.localCode = localCode;
        this.number = number;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.active = active;
    }

    public int getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(int telephoneId) {
        this.telephoneId = telephoneId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTelephoneTypeId() {
        return telephoneTypeId;
    }

    public void setTelephoneTypeId(int telephoneTypeId) {
        this.telephoneTypeId = telephoneTypeId;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
