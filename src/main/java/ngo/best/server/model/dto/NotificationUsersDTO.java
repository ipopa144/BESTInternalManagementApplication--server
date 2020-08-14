package ngo.best.server.model.dto;

import ngo.best.server.model.entity.Notification;
import ngo.best.server.model.entity.User;

import java.util.List;

/**
 * @author Ioana
 */

public class NotificationUsersDTO {

    private NotificationDTO notification;
    private List<UserNotificationDTO> users;

    public NotificationUsersDTO(NotificationDTO notification, List<UserNotificationDTO> users) {
        this.notification = notification;
        this.users = users;
    }

    public NotificationUsersDTO() {
    }

    public NotificationDTO getNotification() {
        return notification;
    }

    public void setNotification(NotificationDTO notification) {
        this.notification = notification;
    }

    public List<UserNotificationDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserNotificationDTO> users) {
        this.users = users;
    }
}
