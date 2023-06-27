package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Store")
@Table(name = "tb_store")
public class Store {

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

    @Column(name = "imagePath")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String profileImage;

    @Column(name = "creationDate")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    @JsonIgnore
    private LocalDateTime lastUpdate;

    @Column(name = "isActive")
    @JsonIgnore
    private boolean active;

    public Store() {
    }

    public Store(int storeId, String name, String documentNumber, int documentTypeId, int addressId, int telephoneId,
            String profileImagePath, LocalDateTime creationDate, LocalDateTime lastUpdate, boolean active) {
        this.storeId = storeId;
        this.name = name;
        this.documentNumber = documentNumber;
        this.documentTypeId = documentTypeId;
        this.addressId = addressId;
        this.telephoneId = telephoneId;
        this.profileImage = profileImagePath;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.active = active;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(int telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
