package ngo.best.server.model.dto;

import ngo.best.server.model.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ioana
 */

public class UserAuthenticationDTO {
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private boolean enabled;
    private LocalDateTime localDateTime;
    private List<Role> roles;

    public UserAuthenticationDTO() {
    }

    public UserAuthenticationDTO(String firstName, String lastName, String nickname, String email, LocalDateTime localDateTime,
                                 Boolean enabled, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.enabled = enabled;
        this.localDateTime = localDateTime;
        this.roles = roles;
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
}
