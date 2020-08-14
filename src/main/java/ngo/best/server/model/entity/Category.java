package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Ioana
 */

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "primaryKey.category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<UserCategory> userCategories = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<DesiredUserCategory> desiredUserCategories = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<NotificationCategory> notificationCategories = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Collection<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }

    public void addUserCategory(UserCategory userCategory) { this.userCategories.add(userCategory); }

    public Collection<DesiredUserCategory> getDesiredUserCategories() {
        return desiredUserCategories;
    }

    public void setDesiredUserCategories(Collection<DesiredUserCategory> desiredUserCategories) {
        this.desiredUserCategories = desiredUserCategories;
    }

    public void addDesiredUserCategory(DesiredUserCategory desiredUserCategory) { this.desiredUserCategories.add(desiredUserCategory); }

    public Collection<NotificationCategory> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(Collection<NotificationCategory> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public void addNotificationCategory(NotificationCategory notificationCategory) { this.notificationCategories.add(notificationCategory); }
}
