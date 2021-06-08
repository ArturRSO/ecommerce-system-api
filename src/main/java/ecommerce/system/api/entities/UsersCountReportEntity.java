package ecommerce.system.api.entities;

import ecommerce.system.api.models.UsersCountReportModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "UsersCountReportEntity")
@Immutable
@Table(name = "vw_usersCountByRole")
public class UsersCountReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "users")
    private int users;

    @Column(name = "admins")
    private int admins;

    @Column(name = "storeAdmins")
    private int storeAdmins;

    @Column(name = "customers")
    private int customers;

    public UsersCountReportEntity() {
    }

    public UsersCountReportEntity(UUID id, int users, int admins, int storeAdmins, int customers) {
        this.id = id;
        this.users = users;
        this.admins = admins;
        this.storeAdmins = storeAdmins;
        this.customers = customers;
    }

    public UsersCountReportModel toModel() {
        return null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getAdmins() {
        return admins;
    }

    public void setAdmins(int admins) {
        this.admins = admins;
    }

    public int getStoreAdmins() {
        return storeAdmins;
    }

    public void setStoreAdmins(int storeAdmins) {
        this.storeAdmins = storeAdmins;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }
}