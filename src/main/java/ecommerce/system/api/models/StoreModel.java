package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class StoreModel {

    private int storeId;
    private String name;
    private String documentNumber;
    private int documentTypeId;
    private int addressId;
    private String profileImagePath;

    @JsonIgnore
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonIgnore
    private boolean active;

    public StoreModel(int storeId, String name, String documentNumber, int documentTypeId, int addressId, String profileImagePath, LocalDateTime creationDate, LocalDateTime lastUpdate, boolean active) {
        this.storeId = storeId;
        this.name = name;
        this.documentNumber = documentNumber;
        this.documentTypeId = documentTypeId;
        this.addressId = addressId;
        this.profileImagePath = profileImagePath;
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
