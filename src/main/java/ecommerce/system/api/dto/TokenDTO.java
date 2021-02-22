package ecommerce.system.api.dto;

import java.time.LocalDateTime;

public class TokenDTO {

    private String token;
    private LocalDateTime expiration;
    private int roleId;

    public TokenDTO(String token, LocalDateTime expiration, int roleId) {
        this.token = token;
        this.expiration = expiration;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
