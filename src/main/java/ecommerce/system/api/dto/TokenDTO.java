package ecommerce.system.api.dto;

import java.time.LocalDateTime;

public class TokenDTO {

    private String token;
    private LocalDateTime expiration;
    private int roleId;
    private int userId;

    public TokenDTO(String token, LocalDateTime expiration, int roleId, int userId) {
        this.token = token;
        this.expiration = expiration;
        this.roleId = roleId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
