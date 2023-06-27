package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "Address")
@Table(name = "tb_address")
public class Address {

    @Id
    @Column(name = "pk_addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private int userId;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "country")
    private String country;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "postalCode")
    private String postalCode;

    @NotNull
    @Size(min = 2, max = 500)
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "number")
    private int number;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "stateCode")
    private String stateCode;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "district")
    private String district;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "complement")
    private String complement;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @JsonIgnore
    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @Column(name = "isActive")
    private boolean active;

    public Address() {
    }

    public Address(int addressId,
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
            boolean active) {
        this.addressId = addressId;
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
        this.active = active;
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
