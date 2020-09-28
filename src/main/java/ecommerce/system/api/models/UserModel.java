package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserModel {

    private final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,16}$";
    private final String cpfCnpjRegex = "/(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)/";

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
    private String password;

    @NotNull
    @Pattern(regexp = cpfCnpjRegex)
    private String documentNumber;

    @NotNull
    private int documentTypeId;

    @NotNull
    private int roleId;

    @NotNull
    private LocalDate birthday;

    private String profileImagePath;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private boolean isActive;

    private List<UserOptionModel> options;

    public UserModel(int userId,
                     String firstName,
                     String lastName,
                     String email,
                     String password,
                     String documentNumber,
                     int documentTypeId,
                     int roleId,
                     LocalDate birthday,
                     String profileImagePath,
                     LocalDateTime creationDate,
                     LocalDateTime lastUpdate,
                     boolean isActive) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.documentNumber = documentNumber;
        this.documentTypeId = documentTypeId;
        this.roleId = roleId;
        this.birthday = birthday;
        this.profileImagePath = profileImagePath;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.isActive = isActive;
    }

    @JsonIgnore
    public int getUserId() {
        return userId;
    }

    @JsonProperty
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

    @JsonIgnore
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

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    @JsonIgnore
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @JsonProperty
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @JsonIgnore
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @JsonProperty
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @JsonIgnore
    public boolean isActive() {
        return isActive;
    }

    @JsonProperty
    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonIgnore
    public List<UserOptionModel> getOptions() {
        return options;
    }

    @JsonProperty
    public void setOptions(List<UserOptionModel> options) {
        this.options = options;
    }
}
