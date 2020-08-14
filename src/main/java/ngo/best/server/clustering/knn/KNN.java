package ngo.best.server.clustering.knn;

import ngo.best.server.clustering.distances.Distance;
import ngo.best.server.clustering.kmeans.CentroidDTO;
import ngo.best.server.clustering.kmeans.ComputedRecord;
import ngo.best.server.clustering.kmeans.Record;
import ngo.best.server.model.entity.User;
import ngo.best.server.utils.DatasetClusteringConverter;

import java.util.*;

/**
 * @author Ioana
 */

public class KNN {

    private static final int k = 20;

    /**
     * Performs the KNN algorithm on the given dataset for the notification
     *
     * @param centroids     The centroids.
     * @param distance      To calculate the distance between two items.
     * @param maxDist       Upper bound for the number of fit users.
     * @param users         The dataset.
     * @return  Final list of selected userDTOs which contains booleans set accordingly about their fitness
                and their membership (first list or second list of selected users).
     */
    public static HashMap<String, ComputedRecord> getFirstKUsersFitForNotification(List<CentroidDTO> centroids, Distance distance,
                                                                             Record notification, double maxDist, List<User> users) {

        HashMap<String, ComputedRecord> fitUsers = new HashMap<>(k);
        HashMap<String, ComputedRecord> recommendedUsers = new HashMap<>(k);
        List<ComputedRecord> computedRecords = new ArrayList<>();
        for (User user : users) {
            Record record = DatasetClusteringConverter.dataFromUser(user);
            double normalDistanceValue = distance.calculate(notification.getFeatures(), record.getFeatures());
            computedRecords.add(new ComputedRecord(record, false, normalDistanceValue));

            // move user towards the centroid which has as dominant value the specific coordinate
            Map<String, Double> newFeaturesCentroid = new HashMap<>();
            record.getFeatures().forEach((name, value) -> {
                centroids.forEach((centroid -> {
                    if (centroid.getDominantMeanMaxValue().equals(name)) {
                        newFeaturesCentroid.put(name, value + record.getDesiredFeatures().get(name) / 10 *
                                (centroid.getCoordinates().get(name) - record.getFeatures().get(name)));
                    }
                }));
            });
            double computedDistanceCentroidValue = distance.calculate(notification.getFeatures(), newFeaturesCentroid);
            computedRecords.add(new ComputedRecord(record, true, computedDistanceCentroidValue));

//            System.out.println(computedDistanceCentroidValue);
        }

        computedRecords.sort(Comparator.comparing(ComputedRecord::getDistanceValue));
//        computedRecords.forEach(computedRecord -> {
//            System.out.println(computedRecord.getRecord().getDescription() + " -> isComputed: " + computedRecord.isComputed() + ", distance: " + computedRecord.getDistanceValue());
//        });

        filterUsers(fitUsers, recommendedUsers, computedRecords, maxDist);

//        System.out.println("----------------------------------------------------------------------------");
//
//        fitUsers.forEach((id, record) -> {
//            System.out.println(id + "-> " + record.getDistanceValue() + "isComputed: " + record.isComputed() + ", isFit: " + record.isFit());
//        });
//
//        System.out.println("----------------------------------------------------------------------------");
//
//        recommendedUsers.forEach((id, record) -> {
//            System.out.println(id + "-> isComputed: " + record.isComputed() + ", isFit: " + record.isFit());
//        });

        fitUsers.putAll(recommendedUsers);
        return fitUsers;
    }

    private static void filterUsers(HashMap<String, ComputedRecord> fitUsers,
                                    HashMap<String, ComputedRecord> recommendedUsers,
                                    List<ComputedRecord> computedRecords, double maxDist) {
        while ((fitUsers.size() < k || recommendedUsers.size() < k) && computedRecords.size() > 0) {
            ComputedRecord computedRecord = computedRecords.get(0);

            if (fitUsers.size() < k) {
                if (fitUsers.get(computedRecord.getRecord().getDescription()) == null) {
                    if (computedRecord.isComputed()) {
                        recommendedUsers.put(computedRecord.getRecord().getDescription(), computedRecord);
                        computedRecord.setFirstRoundRecommended(false);
                    } else {
                        fitUsers.put(computedRecord.getRecord().getDescription(), computedRecord);
                        recommendedUsers.remove(computedRecord.getRecord().getDescription());
                        computedRecord.setFirstRoundRecommended(true);
                    }
                }
            } else {
                if (recommendedUsers.size() < k - 1 && fitUsers.get(computedRecord.getRecord().getDescription()) == null) {
                    recommendedUsers.putIfAbsent(computedRecord.getRecord().getDescription(), computedRecord);
                    computedRecord.setFirstRoundRecommended(false);
                }
            }
            computedRecord.setFit(computedRecord.getDistanceValue() <= maxDist);
            computedRecords.remove(0);
        }
    }
}
