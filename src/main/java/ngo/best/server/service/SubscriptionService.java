package ngo.best.server.service;

import ngo.best.server.model.dto.SubscriptionDTO;
import ngo.best.server.model.entity.Subscription;
import ngo.best.server.model.entity.User;
import ngo.best.server.repository.SubscriptionRepository;
import ngo.best.server.repository.UserRepository;
import ngo.best.server.utils.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public List<Subscription> findAll() { return subscriptionRepository.findAll(); }

    public Subscription save(SubscriptionDTO subscriptionDTO, User user) {
        if (user == null) {
            return null;
        }
        Subscription subscription = DTOConverter.convertSubscriptionDTOToSubscription(subscriptionDTO);
        subscription.setUser(user);
        return this.subscriptionRepository.save(subscription);
    }
}
