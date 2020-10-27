package ecommerce.system.api.entities;

import ecommerce.system.api.models.UserOptionModel;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "UserOptionEntity")
@Table(name = "tb_userOption")
public class UserOptionEntity {

    @Id
    @Column(name = "pk_userOptionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userOptionId;

    @Column(name = "name")
    private String name;

    @Column(name = "elementId")
    private String elementId;

    @Column(name = "route")
    private String route;

    @Column(name = "icon")
    private String icon;

    @Column(name = "samePage")
    private boolean samePage;

    @Column(name = "isActive")
    private boolean active;

    public UserOptionEntity() {
    }

    public UserOptionEntity(UserOptionModel userOption) {
        this.userOptionId = userOption.getUserOptionId();
        this.name = userOption.getName();
        this.elementId = userOption.getElementId();
        this.route = userOption.getRoute();
        this.icon = userOption.getIcon();
        this.samePage = userOption.isSamePage();
        this.active = userOption.isActive();
    }

    public UserOptionModel toModel() {
        return new UserOptionModel(
                this.userOptionId,
                this.name,
                this.elementId,
                this.route,
                this.icon,
                this.samePage,
                this.active
        );
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
