package ecommerce.system.api.services;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.TokenModel;

import java.security.NoSuchAlgorithmException;

public interface IAuthenticationService {

    public TokenModel authenticateUser(CredentialsModel credentials) throws NoSuchAlgorithmException;
}
