package ecommerce.system.api.services.implementations;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.TokenModel;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.tools.JwtHandler;
import ecommerce.system.api.tools.SHAEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final JwtHandler jwtHandler;
    private final SHAEncoder shaEncoder;

    @Autowired
    public AuthenticationService(
            IUserRepository userRepository,
            JwtHandler jwtHandler,
            SHAEncoder shaEncoder) {

        this.userRepository = userRepository;
        this.jwtHandler = jwtHandler;
        this.shaEncoder = shaEncoder;
    }

    @Override
    public TokenModel authenticateUser(CredentialsModel credentials) throws NoSuchAlgorithmException {

        String encodedPassword = this.shaEncoder.encode(credentials.getPassword());
        credentials.setPassword(encodedPassword);

        if (this.userRepository.checkUserCredentials(credentials)) {

            String token = this.jwtHandler.getToken(credentials.getEmail());
            LocalDateTime expirationDate = this.jwtHandler.getExpirationFromToken(token);

            return new TokenModel(token, expirationDate);
        }

        return null;
    }
}
