package ecommerce.system.api.entities;

import ecommerce.system.api.models.StoreModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "StoreEntity")
@Table(name = "tb_store")
public class StoreEntity {

    @Id
    @Column(name = "pk_storeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "fk_documentTypeId")
    private int documentTypeId;

    @Column(name = "fk_addressId")
    private int addressId;

    @Column(name = "fk_telephoneId")
    private int telephoneId;

    @Column(name = "profileImagePath")
    private String profileImagePath;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    private boolean active;

    public StoreEntity() {
    }

    public StoreEntity(StoreModel store) {
        this.storeId = store.getStoreId();
        this.name = store.getName();
        this.documentNumber = store.getDocumentNumber();
        this.documentTypeId = store.getDocumentTypeId();
        this.addressId = store.getAddressId();
        this.telephoneId = store.getTelephoneId();
        this.profileImagePath = store.getProfileImagePath();
        this.creationDate = store.getCreationDate();
        this.lastUpdate = store.getLastUpdate();
        this.active = store.isActive();
    }

    public StoreModel toModel() {
        return new StoreModel(
                this.storeId,
                this.name,
                this.documentNumber,
                this.documentTypeId,
                this.addressId,
                this.telephoneId,
                this.profileImagePath,
                this.creationDate,
                this.lastUpdate,
                this.active
        );
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public int getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(int telephoneId) {
        this.telephoneId = telephoneId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
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
        return !active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
