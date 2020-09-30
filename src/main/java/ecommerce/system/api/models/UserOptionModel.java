package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserOptionModel {

    private int userOptionId;
    private String name;
    private String elementId;
    private String route;
    private boolean samePage;
    private boolean isActive;

    public UserOptionModel(int userOptionId, String name, String elementId, String route, boolean samePage, boolean isActive) {
        this.userOptionId = userOptionId;
        this.name = name;
        this.elementId = elementId;
        this.route = route;
        this.samePage = samePage;
        this.isActive = isActive;
    }

    @JsonIgnore
    public int getUserOptionId() {
        return userOptionId;
    }

    public void setUserOptionId(int userOptionId) {
        this.userOptionId = userOptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isSamePage() {
        return samePage;
    }

    public void setSamePage(boolean samePage) {
        this.samePage = samePage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
