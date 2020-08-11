package ngo.best.server.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Ioana
 */

@Entity
public class Centroid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double maxDistance;

    private String dominantMeanMaxValue;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;

    public Centroid() {
        this.localDateTime = LocalDateTime.now();
    }

    public Centroid(Double maxDistance, String dominantMeanMaxValue) {
        this.maxDistance = maxDistance;
        this.dominantMeanMaxValue = dominantMeanMaxValue;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getDominantMeanMaxValue() {
        return dominantMeanMaxValue;
    }

    public void setDominantMeanMaxValue(String dominantMeanMaxValue) {
        this.dominantMeanMaxValue = dominantMeanMaxValue;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
