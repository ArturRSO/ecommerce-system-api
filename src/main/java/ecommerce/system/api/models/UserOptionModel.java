package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserOptionModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int userOptionId;

    private String name;
    private String elementId;
    private String route;
    private String icon;
    private boolean samePage;

    @JsonIgnore
    private boolean isActive;

    public UserOptionModel(int userOptionId, String name, String elementId, String route, String icon, boolean samePage, boolean isActive) {
        this.userOptionId = userOptionId;
        this.name = name;
        this.elementId = elementId;
        this.route = route;
        this.icon = icon;
        this.samePage = samePage;
        this.isActive = isActive;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
