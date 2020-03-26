package ecommerce.system.api.services;

import ecommerce.system.api.models.CredentialsModel;

import java.security.NoSuchAlgorithmException;

public interface IAuthenticationService {

    public String authenticateUser(CredentialsModel credentials) throws NoSuchAlgorithmException;
}
