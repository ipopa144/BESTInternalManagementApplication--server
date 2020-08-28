package ngo.best.server.model.entity;

import ngo.best.server.model.dto.UserAuthenticationDTO;

import java.util.Collection;
import java.util.List;

/**
 * @author Ioana
 */

public class JwtResponse {
    private UserAuthenticationDTO user;
    private String jwtToken;
    private Boolean subscribed;

    public JwtResponse() {
    }

    public JwtResponse(UserAuthenticationDTO user, String jwtToken, Boolean subscribed) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.subscribed = subscribed;
    }

    public UserAuthenticationDTO getUser() {
        return user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }
}