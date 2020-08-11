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

    public JwtResponse() {
    }

    public JwtResponse(UserAuthenticationDTO user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserAuthenticationDTO getUser() {
        return user;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}