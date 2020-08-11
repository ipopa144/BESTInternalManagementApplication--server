package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Ioana
 */

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "primaryKey.event",
                joinColumns = @JoinColumn(name = "event_id")) })
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "primaryKey")
public class CoreTeamMember {

    private CoreTeamMemberId primaryKey = new CoreTeamMemberId();

    private String position;

    @EmbeddedId
    @JsonIgnore
    public CoreTeamMemberId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CoreTeamMemberId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public User getUser() {
        return getPrimaryKey().getUser();
    }

    public void setUser(User user) {
        getPrimaryKey().setUser(user);
    }

    @Transient
    public Event getEvent() {
        return getPrimaryKey().getEvent();
    }

    public void setEvent(Event event) {
        getPrimaryKey().setEvent(event);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}