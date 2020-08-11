package ngo.best.server.repository;

import ngo.best.server.model.entity.Centroid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface CentroidRepository extends JpaRepository<Centroid, Long> {
    Centroid findByDominantMeanMaxValue(String dominantMeanMaxValue);
}
