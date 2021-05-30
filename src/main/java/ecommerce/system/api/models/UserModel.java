package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserModel {

    private final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,16}$";
    private final String cpfCnpjRegex = "/(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$)/";

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;

    @NotNull
    @Size(min = 2, max = 200)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 500)
    private String lastName;

    @NotNull
    @Pattern(regexp = emailRegex)
    private String email;

    @NotNull
    @Pattern(regexp = passwordRegex)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Pattern(regexp = cpfCnpjRegex)
    private String documentNumber;

    @NotNull
    private int documentTypeId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int roleId;

    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthday;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String profileImage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @JsonIgnore
    private LocalDateTime lastUpdate;

    @JsonIgnore
    private boolean verifiedEmail;

    @JsonIgnore
    private boolean active;

    public UserModel(int userId,
                     String firstName,
                     String lastName,
                     String email,
                     String password,
                     String documentNumber,
                     int documentTypeId,
                     int roleId,
                     LocalDate birthday,
                     String profileImage,
                     LocalDateTime creationDate,
                     LocalDateTime lastUpdate,
                     boolean verifiedEmail,
                     boolean active) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.documentNumber = documentNumber;
        this.documentTypeId = documentTypeId;
        this.roleId = roleId;
        this.birthday = birthday;
        this.profileImage = profileImage;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.verifiedEmail = verifiedEmail;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
