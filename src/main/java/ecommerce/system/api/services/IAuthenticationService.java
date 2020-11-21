package ecommerce.system.api.services;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.TokenModel;

import java.security.NoSuchAlgorithmException;

public interface IAuthenticationService {

    TokenModel authenticateUser(CredentialsModel credentials) throws NoSuchAlgorithmException;
    boolean isLoggedUser(int userId);
    boolean isSystemAdmin();
}
