package ngo.best.server.clustering.kmeans;

import java.util.Map;
import java.util.Objects;

/**
 * @author Ioana
 *
 * Encapsulates all coordinates for a particular cluster centroid.
 */
public class CentroidDTO {

    /**
     * The centroid coordinates.
     */
    private final Map<String, Double> coordinates;

    private Double maxDistance;

    private String dominantMeanMaxValue;

    public CentroidDTO(Map<String, Double> coordinates, String dominantMeanMaxValue, Double maxDistance) {
        this.coordinates = coordinates;
        this.dominantMeanMaxValue = dominantMeanMaxValue;
        this.maxDistance = maxDistance;
    }

    public Map<String, Double> getCoordinates() {
        return coordinates;
    }

    public String getDominantMeanMaxValue() {
        return dominantMeanMaxValue;
    }

    public void setDominantMeanMaxValue(String dominantMeanMaxValue) {
        this.dominantMeanMaxValue = dominantMeanMaxValue;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CentroidDTO centroid = (CentroidDTO) o;
        return Objects.equals(getCoordinates(), centroid.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinates());
    }

    @Override
    public String toString() {
        return "Centroid " + coordinates;
    }
}