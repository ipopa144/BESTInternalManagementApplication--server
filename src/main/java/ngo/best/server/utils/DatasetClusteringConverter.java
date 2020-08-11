package ngo.best.server.utils;

import ngo.best.server.clustering.kmeans.Record;
import ngo.best.server.model.entity.Notification;
import ngo.best.server.model.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ioana
 */

public class DatasetClusteringConverter {

    public static List<Record> datasetFromUsers(List<User> users) {
        List<Record> records = new ArrayList<>();

        for (User user : users) {
            Map<String, Double> tags = new HashMap<>();
            Map<String, Double> desiredTags = new HashMap<>();

            user.getUserCategories().forEach(userCategory -> {
                tags.put(userCategory.getCategory().getName(), userCategory.getGrade());
            });

            user.getDesiredUserCategories().forEach(desiredUserCategory -> {
                desiredTags.put(desiredUserCategory.getCategory().getName(), desiredUserCategory.getGrade());
            });

            records.add(new Record(user.getId().toString(), tags, desiredTags));
        }

        return records;
    }

    public static Record dataFromNotification(Notification notification) {
        Map<String, Double> tags = new HashMap<>();
        System.out.println("Notif: " + notification.getNotificationCategories().size());
        notification.getNotificationCategories().forEach(notificationCategory -> {
            System.out.println("Notif: " + notificationCategory);
            tags.put(notificationCategory.getCategory().getName(), notificationCategory.getGrade());
        });

        return new Record(notification.getId().toString(), tags, null);
    }

    public static Record dataFromUser(User user) {
        Map<String, Double> tags = new HashMap<>();
        Map<String, Double> desiredTags = new HashMap<>();

        user.getUserCategories().forEach(userCategory -> {
            tags.put(userCategory.getCategory().getName(), userCategory.getGrade());
        });

        user.getDesiredUserCategories().forEach(desiredUserCategory -> {
            desiredTags.put(desiredUserCategory.getCategory().getName(), desiredUserCategory.getGrade());
        });

        return new Record(user.getId().toString(), tags, desiredTags);
    }
}
