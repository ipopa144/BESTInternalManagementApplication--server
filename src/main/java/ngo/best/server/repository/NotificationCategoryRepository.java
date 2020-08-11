package ngo.best.server.repository;

import ngo.best.server.model.entity.NotificationCategory;
import ngo.best.server.model.entity.NotificationCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, NotificationCategoryId> {
}
