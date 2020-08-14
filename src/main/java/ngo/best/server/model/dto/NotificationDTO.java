package ngo.best.server.model.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Ioana
 */

public class NotificationDTO {

    private Long id;

    private String text;

    private List<NotificationCategoryDTO> notificationCategories;

    private UpdateUserDTO author;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String text, List<NotificationCategoryDTO> notificationCategories, UpdateUserDTO author) {
        this.id = id;
        this.text = text;
        this.notificationCategories = notificationCategories;
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

    public List<NotificationCategoryDTO> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(List<NotificationCategoryDTO> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public UpdateUserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UpdateUserDTO author) {
        this.author = author;
    }
}
