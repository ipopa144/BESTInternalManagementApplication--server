package ngo.best.server.repository;

import ngo.best.server.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface EventRepository extends JpaRepository<Event, Long> {
}
