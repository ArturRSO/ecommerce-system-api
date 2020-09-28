package ecommerce.system.api.entities;

import ecommerce.system.api.models.TelephoneModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TelephoneEntity")
@Table(name = "tb_telephone")
public class TelephoneEntity {

    @Id
    @Column(name = "pk_telephoneId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int telephoneId;

    @Column(name = "fk_userId")
    private int userId;

    @Column(name = "fk_telephoneTypeId")
    private int telephoneTypeId;

    @Column(name = "internationalCode")
    private String internationalCode;

    @Column(name = "localCode")
    private int localCode;

    @Column(name = "number")
    private String number;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    private boolean isActive;

    public TelephoneEntity() {
    }

    public TelephoneEntity(TelephoneModel telephone) {
        this.telephoneId = telephone.getTelephoneId();
        this.userId = telephone.getUserId();
        this.telephoneTypeId = telephone.getTelephoneTypeId();
        this.internationalCode = telephone.getInternationalCode();
        this.localCode = telephone.getLocalCode();
        this.number = telephone.getNumber();
        this.creationDate = telephone.getCreationDate();
        this.lastUpdate = telephone.getLastUpdate();
        this.isActive = telephone.isActive();
    }

    public TelephoneModel toModel() {
        return new TelephoneModel(
                this.telephoneId,
                this.userId,
                this.telephoneTypeId,
                this.internationalCode,
                this.localCode,
                this.number,
                this.creationDate,
                this.lastUpdate,
                this.isActive
        );
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
