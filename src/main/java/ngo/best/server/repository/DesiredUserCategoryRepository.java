package ngo.best.server.repository;

import ngo.best.server.model.entity.DesiredUserCategory;
import ngo.best.server.model.entity.DesiredUserCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface DesiredUserCategoryRepository extends JpaRepository<DesiredUserCategory, DesiredUserCategoryId> {
}
