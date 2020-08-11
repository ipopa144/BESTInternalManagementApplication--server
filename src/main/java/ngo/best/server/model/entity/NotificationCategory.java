package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Ioana
 */

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.notification",
                joinColumns = @JoinColumn(name = "notification_id")),
        @AssociationOverride(name = "primaryKey.category",
                joinColumns = @JoinColumn(name = "category_id")) })
public class NotificationCategory {

    private NotificationCategoryId primaryKey = new NotificationCategoryId();

    private Double grade;

    public NotificationCategory() {}

    @EmbeddedId
    @JsonIgnore
    public NotificationCategoryId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(NotificationCategoryId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Notification getNotification() {
        return getPrimaryKey().getNotification();
    }

    public void setNotification(Notification notification) {
        getPrimaryKey().setNotification(notification);
    }

    @Transient
    public Category getCategory() { return getPrimaryKey().getCategory(); }

    public void setCategory(Category category) {
        getPrimaryKey().setCategory(category);
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
