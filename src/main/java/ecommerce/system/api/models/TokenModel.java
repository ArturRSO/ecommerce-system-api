package ecommerce.system.api.models;

import java.time.LocalDateTime;

public class TokenModel {

    private String token;
    private LocalDateTime expiration;

    public TokenModel(String token, LocalDateTime expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
