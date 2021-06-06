package ecommerce.system.api.models;

import java.util.UUID;

public class UsersCountReportModel {

    private UUID id;
    private int users;
    private int storeAdmins;

    public UsersCountReportModel(UUID id, int users, int storeAdmins) {
        this.id = id;
        this.users = users;
        this.storeAdmins = storeAdmins;
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