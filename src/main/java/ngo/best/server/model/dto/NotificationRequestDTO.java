package ngo.best.server.model.dto;

import java.util.Map;

/**
 * @author Ioana
 */

public class NotificationRequestDTO {
    private String text;

    private String title;

    private Map<String, Double> notificationCategories;

    public NotificationRequestDTO() {}

    public NotificationRequestDTO(String text, String title, Map<String, Double> notificationCategories) {
        this.text = text;
        this.title = title;
        this.notificationCategories = notificationCategories;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Double> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(Map<String, Double> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
