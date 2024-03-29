package ngo.best.server.model.dto;

import java.util.List;

/**
 * @author Ioana
 */

public class EventDTO {

    private Long id;
    private String name;
    private int firstYear;
    private String description;
    private UpdateUserDTO mainOrganizer;
    private List<CoreTeamMemberDTO> coreTeamMembers;

    public EventDTO() {
    }

    public EventDTO(String name, int firstYear, String description) {
        this.name = name;
        this.firstYear = firstYear;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UpdateUserDTO getMainOrganizer() {
        return mainOrganizer;
    }

    public void setMainOrganizer(UpdateUserDTO mainOrganizer) {
        this.mainOrganizer = mainOrganizer;
    }

    public List<CoreTeamMemberDTO> getCoreTeamMembers() {
        return coreTeamMembers;
    }

    public void setCoreTeamMembers(List<CoreTeamMemberDTO> coreTeamMembers) {
        this.coreTeamMembers = coreTeamMembers;
    }
}
