package ecommerce.system.api.entities;

import ecommerce.system.api.models.UserModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "UserEntity")
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @Column(name = "pk_userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "fk_documentTypeId")
    private int documentTypeId;

    @Column(name = "fk_roleId")
    private int roleId;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "imagePath")
    private String profileImagePath;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "verifiedEmail")
    private boolean verifiedEmail;

    @Column(name = "isActive")
    private boolean active;

    public UserEntity() {
    }

    public UserEntity(UserModel user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.documentNumber = user.getDocumentNumber();
        this.documentTypeId = user.getDocumentTypeId();
        this.roleId = user.getRoleId();
        this.birthday = user.getBirthday();
        this.profileImagePath = user.getProfileImagePath();
        this.creationDate = user.getCreationDate();
        this.lastUpdate = user.getLastUpdate();
        this.verifiedEmail = user.isVerifiedEmail();
        this.active = user.isActive();
    }

    public UserModel toModel() {
        return new UserModel(
                this.userId,
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                this.documentNumber,
                this.documentTypeId,
                this.roleId,
                this.birthday,
                this.profileImagePath,
                this.creationDate,
                this.lastUpdate,
                this.verifiedEmail,
                this.active
        );
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
