package ngo.best.server.model.dto;

import ngo.best.server.model.entity.Role;

import java.util.List;

/**
 * @author Ioana
 */

public class UserNotificationDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean fit;
    private Boolean firstRoundRecommended;
    private Boolean computed;
    private SubscriptionDTO subscriptionDTO;

    public UserNotificationDTO(Long id, String firstName, String lastName, Boolean fit, Boolean computed,
                               Boolean firstRoundRecommended) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fit = fit;
        this.firstRoundRecommended = firstRoundRecommended;
        this.computed = computed;
    }

    public UserNotificationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getFit() {
        return fit;
    }

    public void setFit(Boolean fit) {
        this.fit = fit;
    }

    public Boolean getComputed() {
        return computed;
    }

    public void setComputed(Boolean computed) {
        this.computed = computed;
    }

    public Boolean getFirstRoundRecommended() {
        return firstRoundRecommended;
    }

    public void setFirstRoundRecommended(Boolean firstRoundRecommended) {
        this.firstRoundRecommended = firstRoundRecommended;
    }

    public SubscriptionDTO getSubscriptionDTO() {
        return subscriptionDTO;
    }

    public void setSubscriptionDTO(SubscriptionDTO subscriptionDTO) {
        this.subscriptionDTO = subscriptionDTO;
    }
}
