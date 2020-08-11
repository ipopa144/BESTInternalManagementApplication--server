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
    private Boolean isFit;

    public UserNotificationDTO(Long id, String firstName, String lastName, Boolean isFit) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isFit = isFit;
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
        return isFit;
    }

    public void setFit(Boolean fit) {
        isFit = fit;
    }
}
