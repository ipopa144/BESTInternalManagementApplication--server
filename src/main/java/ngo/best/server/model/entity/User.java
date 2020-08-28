package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Ioana
 */

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String nickname;

    private String email;

    private String password;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnoreProperties("users")
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "primaryKey.user", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("coreTeamMembers")
    private Collection<CoreTeamMember> coreTeamMembers = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.user", cascade = CascadeType.ALL)
    private Collection<UserCategory> userCategories = new HashSet<>();

    @OneToMany(mappedBy = "primaryKey.user", cascade = CascadeType.ALL)
    private Collection<DesiredUserCategory> desiredUserCategories = new HashSet<>();

    @OneToMany(mappedBy="mainOrganizer")
    private Collection<Event> eventsMainOrganizer;

    @OneToMany(mappedBy="author")
//    @JsonIgnoreProperties("author")
    private Collection<Notification> notifications;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Subscription subscription;

    public User() {
        this.localDateTime = LocalDateTime.now();
    }

    public User(String firstName, String lastName, String nickname, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.localDateTime = LocalDateTime.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<CoreTeamMember> getCoreTeamMembers() {
        return coreTeamMembers;
    }

    public void setCoreTeamMembers(Collection<CoreTeamMember> coreTeamMembers) {
        this.coreTeamMembers = coreTeamMembers;
    }

    public void addCoreTeamMember(CoreTeamMember coreTeamMember) {
        this.coreTeamMembers.add(coreTeamMember);
    }

    public Collection<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Collection<UserCategory> userCategories) {
        this.userCategories = userCategories;
    }

    public void addUserCategory(UserCategory userCategory) { this.userCategories.add(userCategory); }

    public Collection<DesiredUserCategory> getDesiredUserCategories() {
        return desiredUserCategories;
    }

    public void setDesiredUserCategories(Collection<DesiredUserCategory> desiredUserCategories) {
        this.desiredUserCategories = desiredUserCategories;
    }

    public void addDesiredUserCategory(DesiredUserCategory desiredUserCategory) { this.desiredUserCategories.add(desiredUserCategory); }

    public Collection<Event> getEventsMainOrganizer() {
        return eventsMainOrganizer;
    }

    public void setEventsMainOrganizer(Collection<Event> eventsMainOrganizer) {
        this.eventsMainOrganizer = eventsMainOrganizer;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}