package ngo.best.server.repository;

import ngo.best.server.model.entity.Category;
import ngo.best.server.model.entity.Centroid;
import ngo.best.server.model.entity.CentroidCategory;
import ngo.best.server.model.entity.CentroidCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ioana
 */

public interface CentroidCategoryRepository extends JpaRepository<CentroidCategory, CentroidCategoryId> {
//    List<CentroidCategory> findAllByCentroid(Centroid centroid);
}
