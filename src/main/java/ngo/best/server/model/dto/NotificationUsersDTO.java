package ngo.best.server.model.dto;

import ngo.best.server.model.entity.Notification;
import ngo.best.server.model.entity.User;

import java.util.List;

/**
 * @author Ioana
 */

public class NotificationUsersDTO {

    private Notification notification;
    private List<UserNotificationDTO> users;

    public NotificationUsersDTO(Notification notification, List<UserNotificationDTO> users) {
        this.notification = notification;
        this.users = users;
    }

    public NotificationUsersDTO() {
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<UserNotificationDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserNotificationDTO> users) {
        this.users = users;
    }
}
