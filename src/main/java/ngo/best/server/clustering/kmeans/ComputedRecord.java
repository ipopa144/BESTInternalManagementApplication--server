package ngo.best.server.clustering.kmeans;

/**
 * @author Ioana
 */

public class ComputedRecord {
    private Record record;
    private boolean isComputed;
    private boolean isFit;
    private boolean firstRoundRecommended;
    private double distanceValue;

    public ComputedRecord(Record record, boolean isComputed, double distanceValue) {
        this.record = record;
        this.isComputed = isComputed;
        this.distanceValue = distanceValue;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public boolean isComputed() {
        return isComputed;
    }

    public void setComputed(boolean computed) {
        isComputed = computed;
    }

    public double getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(double distanceValue) {
        this.distanceValue = distanceValue;
    }

    public boolean isFit() {
        return isFit;
    }

    public void setFit(boolean fit) {
        isFit = fit;
    }

    public boolean isFirstRoundRecommended() {
        return firstRoundRecommended;
    }

    public void setFirstRoundRecommended(boolean firstRoundRecommended) {
        this.firstRoundRecommended = firstRoundRecommended;
    }
}
