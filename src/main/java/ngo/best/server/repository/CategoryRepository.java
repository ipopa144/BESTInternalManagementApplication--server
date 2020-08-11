package ngo.best.server.repository;

import ngo.best.server.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
