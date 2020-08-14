package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class CoreTeamMemberDTO {

    private EventUserDTO event;
    private UpdateUserDTO userEvent;
    private String position;

    public CoreTeamMemberDTO() {
    }

    public CoreTeamMemberDTO(EventUserDTO event, UpdateUserDTO userEvent, String position) {
        this.event = event;
        this.userEvent = userEvent;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EventUserDTO getEvent() {
        return event;
    }

    public void setEvent(EventUserDTO event) {
        this.event = event;
    }

    public UpdateUserDTO getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(UpdateUserDTO userEvent) {
        this.userEvent = userEvent;
    }
}
