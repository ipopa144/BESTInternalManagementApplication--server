package ngo.best.server.service;

import ngo.best.server.clustering.distances.Distance;
import ngo.best.server.clustering.distances.EuclideanDistance;
import ngo.best.server.clustering.kmeans.*;
import ngo.best.server.clustering.knn.KNN;
import ngo.best.server.model.dto.UserNotificationDTO;
import ngo.best.server.model.entity.*;
import ngo.best.server.repository.*;
import ngo.best.server.utils.DTOConverter;
import ngo.best.server.utils.DatasetClusteringConverter;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

/**
 * @author Ioana
 */

@Service
public class ClusteringService {

    private static final Integer MONTHS_PERIOD = 6;
    private final Distance distance = new EuclideanDistance();
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CentroidRepository centroidRepository;
    private final CentroidCategoryRepository centroidCategoryRepository;
    private final SubscriptionRepository subscriptionRepository;

    public ClusteringService(UserRepository userRepository, CategoryRepository categoryRepository,
                             CentroidRepository centroidRepository, CentroidCategoryRepository centroidCategoryRepository,
                             SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.centroidRepository = centroidRepository;
        this.centroidCategoryRepository = centroidCategoryRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Generates the clusters for the given dataset and computes the mean max
     * distance for each of them
     *
     * @param users The dataset.
     */
    public void generateClusters(List<User> users) {

        AtomicBoolean generateNewClusters = new AtomicBoolean(true);
        centroidRepository.findAll().forEach(centroid -> {
            Period period = Period.between(LocalDateTime.now().toLocalDate(), centroid.getLocalDateTime().toLocalDate());
            if(period.getMonths() <= MONTHS_PERIOD)
                generateNewClusters.set(false);
        });

        if (generateNewClusters.get()) {
            centroidCategoryRepository.deleteAll();
            centroidRepository.deleteAll();
            List<Record> records = DatasetClusteringConverter.datasetFromUsers(users);
            Map<CentroidDTO, List<Record>> clusters = KMeans.fit(records, distance, 1000);
            KMeans.computeMeanMaxDistance(distance);

            // Print the cluster configuration
            clusters.forEach((key, recordsList) -> {
//                System.out.println("------------------------------ CLUSTER -----------------------------------");
                CentroidDTO sortedCentroid = sortedCentroid(key);
                key.setDominantMeanMaxValue(sortedCentroid.getDominantMeanMaxValue());
                key.setMaxDistance(sortedCentroid.getMaxDistance());
                Centroid savedCentroid = centroidRepository.save(new Centroid(key.getMaxDistance(), key.getDominantMeanMaxValue()));
                key.getCoordinates().forEach((categoryName, meanValue) -> {
                    Category category = categoryRepository.findByName(categoryName);
                    CentroidCategory centroidCategory = new CentroidCategory();
                    centroidCategory.setCategory(category);
                    centroidCategory.setCentroid(savedCentroid);
                    centroidCategory.setMeanValue(meanValue);
                    centroidCategoryRepository.save(centroidCategory);
                });
//                System.out.println(key);
//                System.out.println("DominantMinMaxValue: " + key.getDominantMeanMaxValue());
//                String members = String.join(", ", recordsList
//                        .stream()
//                        .map(Record::getDescription)
//                        .collect(toSet()));
//                System.out.print(members);
//
//                System.out.println("\nMax Distance: " + key.getMaxDistance());
//
//                System.out.println();
//                System.out.println();
            });

        }
    }

    private CentroidDTO sortedCentroid(CentroidDTO key) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(key
                .getCoordinates()
                .entrySet());
        entries.sort((e1, e2) -> e2
                .getValue()
                .compareTo(e1.getValue()));

        Map<String, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return new CentroidDTO(sorted, entries.get(0).getKey(), key.getMaxDistance());
    }

    /**
     * Performs the KNN algorithm on the given dataset for the input notification.
     *
     * @param notification   The notification.
     * @return Final list of selected users.
     */
    public List<UserNotificationDTO> getFirstKUsersFitForNotification(Notification notification) {
        Record recordFromNotification = DatasetClusteringConverter.dataFromNotification(notification);
        List<UserNotificationDTO> recommendedUsers = new ArrayList<>();

        HashMap<String, ComputedRecord> firstKUsersFitForNotification = KNN.getFirstKUsersFitForNotification
                (getCentroids(), distance, recordFromNotification, KMeans.maxDist, userRepository.findAll());

        firstKUsersFitForNotification.forEach((id, record) -> {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            if (user.isPresent()) {
                UserNotificationDTO userNotificationDTO = new UserNotificationDTO(Long.valueOf(id), user.get().getFirstName(),
                        user.get().getLastName(), record.isFit(), record.isComputed(), record.isFirstRoundRecommended());
                Optional<Subscription> subscription = subscriptionRepository.findByUser(user.get());
                subscription.ifPresent(value -> userNotificationDTO.setSubscriptionDTO(DTOConverter.convertSubscriptionToSubscriptionDTO(value)));
                recommendedUsers.add(userNotificationDTO);
            }
        });

        return recommendedUsers;
    }

    public List<CentroidDTO> getCentroids() {
        List<Centroid> centroids = centroidRepository.findAll();
        List<CentroidDTO> centroidDTOS = new ArrayList<>();
        centroids.forEach(centroid -> {
            List<CentroidCategory> centroidCategoryList = centroidCategoryRepository.findAll().stream()
                    .filter(c -> c.getPrimaryKey().getCentroid().getId().equals(centroid.getId())).collect(toList());
            Map<String, Double> coordinates = new HashMap<>();
            for (CentroidCategory centroidCategory : centroidCategoryList) {
                coordinates.put(centroidCategory.getCategory().getName(), centroidCategory.getMeanValue());
            }
            centroidDTOS.add(new CentroidDTO(coordinates, centroid.getDominantMeanMaxValue(), centroid.getMaxDistance()));
        });
        return centroidDTOS;
    }
}
