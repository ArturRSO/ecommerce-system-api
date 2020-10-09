package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AddressModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int addressId;

    @NotNull
    private int userId;

    @NotNull
    @Size(min = 2, max = 200)
    private String country;

    @NotNull
    @Size(min = 8, max = 8)
    private String postalCode;

    @NotNull
    @Size(min = 2, max = 500)
    private String address;

    @NotNull
    private int number;

    @NotNull
    @Size(min = 2, max = 2)
    private String stateCode;

    @NotNull
    @Size(min = 2, max = 200)
    private String city;

    @NotNull
    @Size(min = 2, max = 200)
    private String district;

    @NotNull
    @Size(min = 2, max = 200)
    private String complement;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonIgnore
    private boolean isActive;

    public AddressModel(int addressId,
                        int userId,
                        String country,
                        String postalCode,
                        String address,
                        int number,
                        String stateCode,
                        String city,
                        String district,
                        String complement,
                        LocalDateTime creationDate,
                        LocalDateTime lastUpdate,
                        boolean isActive) {
        this.addressId = addressId;
        this.userId = userId;
        this.country = country;
        this.postalCode = postalCode;
        this.address = address;
        this.number = number;
        this.stateCode = stateCode;
        this.city = city;
        this.district = district;
        this.complement = complement;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.isActive = isActive;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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
