package ecommerce.system.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CredentialsDTO {

    @NotNull
    @Size(min = 7, max = 200)
    private String email;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    public CredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
