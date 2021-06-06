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
@Table(name = "vw_usersCount")
public class UsersCountReportEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "users")
    private int users;

    @Column(name = "storeAdmins")
    private int storeAdmins;

    public UsersCountReportEntity() {
    }

    public UsersCountReportEntity(UUID id, int users, int storeAdmins) {
        this.id = id;
        this.users = users;
        this.storeAdmins = storeAdmins;
    }

    public UsersCountReportModel toModel() {
        return new UsersCountReportModel(this.id, this.users, this.storeAdmins);
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

    public int getStoreAdmins() {
        return storeAdmins;
    }

    public void setStoreAdmins(int storeAdmins) {
        this.storeAdmins = storeAdmins;
    }
}