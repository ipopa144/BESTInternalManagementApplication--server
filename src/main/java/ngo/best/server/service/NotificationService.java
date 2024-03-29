package ngo.best.server.service;

import ngo.best.server.model.dto.NotificationDTO;
import ngo.best.server.model.dto.NotificationRequestDTO;
import ngo.best.server.model.dto.UserNotificationDTO;
import ngo.best.server.model.entity.*;
import ngo.best.server.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final CategoryRepository categoryRepository;

    private final NotificationCategoryRepository notificationCategoryRepository;

    private final ClusteringService clusteringService;

    public NotificationService(NotificationRepository notificationRepository,
                               CategoryRepository categoryRepository,
                               NotificationCategoryRepository notificationCategoryRepository,
                               ClusteringService clusteringService) {
        this.notificationRepository = notificationRepository;
        this.categoryRepository = categoryRepository;
        this.notificationCategoryRepository = notificationCategoryRepository;
        this.clusteringService = clusteringService;
    }

    public List<Notification> findAll() { return notificationRepository.findAll(); }

    /**
     * Saves the notification into the database.
     *
     * @param notificationDTO   The notification info (description, grades (requirements))
     * @param author            The sender (author) of the notification (User).
     * @return The saved notification.
     */
    public Notification save(NotificationRequestDTO notificationDTO, User author) {

        Notification notification = new Notification();
        notification.setText(notificationDTO.getText());
        notification.setTitle(notificationDTO.getTitle());
        if (author != null) {
            notification.setAuthor(author);
            notification = notificationRepository.save(notification);
        }
        Optional<Notification> optionalNotification = notificationRepository.findById(notification.getId());
        if(optionalNotification.isPresent()) {
            List<NotificationCategory> notificationCategories = new ArrayList<>();
            notificationDTO.getNotificationCategories().forEach((categoryName, grade) -> {
                Category category = categoryRepository.findByName(categoryName);
                if (category != null) {
                    NotificationCategory notificationCategory = new NotificationCategory();
                    notificationCategory.setCategory(category);
                    notificationCategory.setNotification(optionalNotification.get());
                    notificationCategory.setGrade(grade);
                    NotificationCategory savedNotificationCategory = notificationCategoryRepository.save(notificationCategory);
                    notificationCategories.add(savedNotificationCategory);
                }
            });
            notification.setNotificationCategories(notificationCategories);
            return notification;
        }
        return null;
    }

    public Notification delete(Long notificationId) {
        final Optional<Notification> notification = notificationRepository.findById(notificationId);
        if(notification.isPresent()) {
            notificationRepository.delete(notification.get());
            return notification.get();
        }
        return null;
    }

    /**
     * Generates the selected users for the input notification
     * and returns a list of them along with their subscriptions.
     *
     * @param notification  The dataset.
     * @return Final list of selected users
     */
    public List<UserNotificationDTO> getSelectedUsersForNotification(Notification notification) {
        Optional<Notification> foundNotification = notificationRepository.findById(notification.getId());
        return foundNotification.map(clusteringService::getFirstKUsersFitForNotification).orElse(null);
    }

}
