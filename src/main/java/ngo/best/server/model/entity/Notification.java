package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Ioana
 */

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToMany(mappedBy = "primaryKey.notification", cascade = CascadeType.ALL)
    private Collection<NotificationCategory> notificationCategories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="notification_author")
    @JsonIgnoreProperties("notifications")
    private User author;

    public Notification() {
    }

    public Notification(String text, User author) {
        this.text = text;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<NotificationCategory> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(Collection<NotificationCategory> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public void addNotificationCategory(NotificationCategory notificationCategory) { this.notificationCategories.add(notificationCategory); }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
