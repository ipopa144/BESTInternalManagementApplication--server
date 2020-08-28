package ngo.best.server.model.entity;

import ngo.best.server.model.dto.UserDTO;

/**
 * @author Ioana
 */

public class JwtPreAuthenticateResponse {
    private UserDTO user;
    private Boolean subscribed;

    public JwtPreAuthenticateResponse() {
    }

    public JwtPreAuthenticateResponse(UserDTO user, Boolean subscribed) {
        this.user = user;
        this.subscribed = subscribed;
    }

    public UserDTO getUser() {
        return user;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }
}
