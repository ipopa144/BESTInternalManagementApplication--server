package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Ioana
 */

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private int firstYear;

    private String description;

    @ManyToOne
    @JoinColumn(name="main_organizer")
//    @JsonIgnoreProperties("eventsMainOrganizer")
    private User mainOrganizer;

    @OneToMany(mappedBy = "primaryKey.event", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("coreTeamMembers")
    private Collection<CoreTeamMember> coreTeamMembers = new HashSet<>();

    public Event() {}

    public Event(String name, int firstYear, String description) {
        this.name = name;
        this.firstYear = firstYear;
        this.description = description;
    }

    public Event(String name, int firstYear, String description, User mainOrganizer, Collection<CoreTeamMember> coreTeamMembers) {
        this.name = name;
        this.firstYear = firstYear;
        this.description = description;
        this.mainOrganizer = mainOrganizer;
        this.coreTeamMembers = coreTeamMembers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMainOrganizer() {
        return mainOrganizer;
    }

    public void setMainOrganizer(User mainOrganizer) {
        this.mainOrganizer = mainOrganizer;
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
}