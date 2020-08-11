package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class SubscriptionDTO {

    private String endpoint;

    private String expirationTime;

    private String p256dh;

    private String auth;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(String endpoint, String expirationTime, String p256dh, String auth) {
        this.endpoint = endpoint;
        this.expirationTime = expirationTime;
        this.p256dh = p256dh;
        this.auth = auth;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getP256dh() {
        return p256dh;
    }

    public void setP256dh(String p256dh) {
        this.p256dh = p256dh;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
