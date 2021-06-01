package ecommerce.system.api.services;

import ecommerce.system.api.dto.CredentialsDTO;
import ecommerce.system.api.dto.TokenDTO;

import java.security.NoSuchAlgorithmException;

public interface IAuthenticationService {

    TokenDTO authenticateUser(CredentialsDTO credentials) throws NoSuchAlgorithmException;
    boolean isLoggedUser(int userId);
    boolean isNotSystemAdmin();
}
