package ecommerce.system.api.models;

import java.util.UUID;

public class UsersCountReportModel {

    private UUID id;
    private int users;
    private int admins;
    private int storeAdmins;
    private int customers;

    public UsersCountReportModel(UUID id, int users, int admins, int storeAdmins, int customers) {
        this.id = id;
        this.users = users;
        this.admins = admins;
        this.storeAdmins = storeAdmins;
        this.customers = customers;
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