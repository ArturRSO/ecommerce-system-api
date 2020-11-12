package ecommerce.system.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
public class TokenConfiguration {

    private int emailVerifyExpiration;
    private String emailVerifyKey;
    private int jwtExpiration;
    private String jwtKey;
    private int passwordRecoverExpiration;
    private String passwordRecoverKey;
    private int storeAdminExpiration;
    private String storeAdminKey;

    public int getEmailVerifyExpiration() {
        return emailVerifyExpiration;
    }

    public void setEmailVerifyExpiration(int emailVerifyExpiration) {
        this.emailVerifyExpiration = emailVerifyExpiration;
    }

    public String getEmailVerifyKey() {
        return emailVerifyKey;
    }

    public void setEmailVerifyKey(String emailVerifiyKey) {
        this.emailVerifyKey = emailVerifiyKey;
    }

    public int getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(int jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    public int getPasswordRecoverExpiration() {
        return passwordRecoverExpiration;
    }

    public void setPasswordRecoverExpiration(int passwordRecoverExpiration) {
        this.passwordRecoverExpiration = passwordRecoverExpiration;
    }

    public String getPasswordRecoverKey() {
        return passwordRecoverKey;
    }

    public void setPasswordRecoverKey(String passwordRecoverKey) {
        this.passwordRecoverKey = passwordRecoverKey;
    }

    public int getStoreAdminExpiration() {
        return storeAdminExpiration;
    }

    public void setStoreAdminExpiration(int storeAdminExpiration) {
        this.storeAdminExpiration = storeAdminExpiration;
    }

    public String getStoreAdminKey() {
        return storeAdminKey;
    }

    public void setStoreAdminKey(String storeAdminKey) {
        this.storeAdminKey = storeAdminKey;
    }
}
