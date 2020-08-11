package ngo.best.server.repository;

import ngo.best.server.model.entity.UserCategory;
import ngo.best.server.model.entity.UserCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface UserCategoryRepository extends JpaRepository<UserCategory, UserCategoryId> {
}