package ngo.best.server.repository;

import ngo.best.server.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
