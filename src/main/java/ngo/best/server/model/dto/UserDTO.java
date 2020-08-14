package ngo.best.server.model.dto;

import ngo.best.server.model.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ioana
 */

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private boolean enabled;
    private List<Role> roles;
    private LocalDateTime localDateTime;
    private List<UserCategoryDTO> userCategories;
    private List<DesiredUserCategoryDTO> desiredUserCategories;
    private List<CoreTeamMemberDTO> coreTeamMembers;
    private List<EventUserDTO> eventsMainOrganizer;

    public UserDTO() {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserCategoryDTO> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<UserCategoryDTO> userCategories) {
        this.userCategories = userCategories;
    }

    public List<DesiredUserCategoryDTO> getDesiredUserCategories() {
        return desiredUserCategories;
    }

    public void setDesiredUserCategories(List<DesiredUserCategoryDTO> desiredUserCategories) {
        this.desiredUserCategories = desiredUserCategories;
    }

    public List<CoreTeamMemberDTO> getCoreTeamMembers() {
        return coreTeamMembers;
    }

    public void setCoreTeamMembers(List<CoreTeamMemberDTO> coreTeamMembers) {
        this.coreTeamMembers = coreTeamMembers;
    }

    public List<EventUserDTO> getEventsMainOrganizer() {
        return eventsMainOrganizer;
    }

    public void setEventsMainOrganizer(List<EventUserDTO> eventsMainOrganizer) {
        this.eventsMainOrganizer = eventsMainOrganizer;
    }
}
