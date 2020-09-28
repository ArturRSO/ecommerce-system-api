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

    @Column(name = "samePage")
    private boolean samePage;

    @Column(name = "isActive")
    private boolean isActive;

    public UserOptionEntity() {
    }

    public UserOptionEntity(int userOptionId, String name, String elementId, String route, boolean samePage, boolean isActive, Set<UserEntity> users) {
        this.userOptionId = userOptionId;
        this.name = name;
        this.elementId = elementId;
        this.route = route;
        this.samePage = samePage;
        this.isActive = isActive;
    }

    public UserOptionModel toModel() {
        return new UserOptionModel(
                this.userOptionId,
                this.name,
                this.elementId,
                this.route,
                this.samePage,
                this.isActive
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
