package ngo.best.server.clustering.kmeans;

import java.util.Map;
import java.util.Objects;

/**
 * @author Ioana
 */

public class Record {
    /**
     * The record description. User's name
     */
    private final String description;

    /**
     * Encapsulates all attributes and their corresponding given values
     */
    private final Map<String, Double> features;

    /**
     * Encapsulates all attributes and their corresponding desired values
     */
    private final Map<String, Double> desiredFeatures;

    public Record(String description, Map<String, Double> features, Map<String, Double> desiredFeatures) {
        this.description = description;
        this.features = features;
        this.desiredFeatures = desiredFeatures;
    }

    public Record(Map<String, Double> features, Map<String, Double> desiredFeatures) {
        this("", features, desiredFeatures);
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Double> getFeatures() {
        return features;
    }

    public Map<String, Double> getDesiredFeatures() {
        return desiredFeatures;
    }

    @Override
    public String toString() {
        String prefix = description == null || description
                .trim()
                .isEmpty() ? "Record" : description;

        return prefix + ": " + features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return Objects.equals(getDescription(), record.getDescription()) && Objects.equals(getFeatures(), record.getFeatures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getFeatures());
    }
}