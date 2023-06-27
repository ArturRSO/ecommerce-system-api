package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "User")
@Table(name = "tb_user")
public class User {

    @Transient
    private final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

    @Transient
    private final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,16}$";

    @Transient
    private final String cpfCnpjRegex = "/(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$)/";

    @Id
    @Column(name = "pk_userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "firstName")
    @NotNull
    @Size(min = 2, max = 200)
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @Size(min = 2, max = 500)
    private String lastName;

    @Column(name = "email")
    @NotNull
    @Pattern(regexp = emailRegex)
    private String email;

    @Column(name = "password")
    @NotNull
    @Pattern(regexp = passwordRegex)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "documentNumber")
    @NotNull
    @Pattern(regexp = cpfCnpjRegex)
    private String documentNumber;

    @Column(name = "fk_documentTypeId")
    @NotNull
    private int documentTypeId;

    @Column(name = "fk_roleId")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int roleId;

    @Column(name = "birthday")
    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthday;

    @Column(name = "imagePath")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String profileImage;

    @Column(name = "creationDate")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    @JsonIgnore
    private LocalDateTime lastUpdate;

    @Column(name = "verifiedEmail")
    @JsonIgnore
    private boolean verifiedEmail;

    @Column(name = "isActive")
    @JsonIgnore
    private boolean active;

    public User() {
    }

    public User(int userId,
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
