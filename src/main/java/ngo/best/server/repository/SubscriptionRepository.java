package ngo.best.server.repository;

import ngo.best.server.model.entity.Subscription;
import ngo.best.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ioana
 */

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUser(User user);
}
