package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class CoreTeamMemberDTO {

    private Long userId;

    private String position;

    public CoreTeamMemberDTO() {
    }

    public CoreTeamMemberDTO(Long userId, String position) {
        this.userId = userId;
        this.position = position;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
