package ecommerce.system.api.services.implementations;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.tools.JwtHandler;
import ecommerce.system.api.tools.PasswordRecoverHandler;
import ecommerce.system.api.tools.SHAEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final JwtHandler jwtHandler;
    private final PasswordRecoverHandler passwordRecoverHandler;
    private final SHAEncoder shaEncoder;

    @Autowired
    public AuthenticationService(
            IUserRepository userRepository,
            JwtHandler jwtHandler,
            PasswordRecoverHandler passwordRecoverHandler,
            SHAEncoder shaEncoder) {

        this.userRepository = userRepository;
        this.jwtHandler = jwtHandler;
        this.passwordRecoverHandler = passwordRecoverHandler;
        this.shaEncoder = shaEncoder;
    }

    @Override
    public String authenticateUser(CredentialsModel credentials) throws NoSuchAlgorithmException {

        String encodedPassword = this.shaEncoder.encode(credentials.getPassword());
        credentials.setPassword(encodedPassword);

        if (this.userRepository.checkUserCredentials(credentials)) {

            return this.jwtHandler.getToken(credentials.getEmail());
        }

        return null;
    }

    @Override
    public boolean sendPasswordRecoverEmail(String email) throws Exception {

        if (this.userRepository.checkUserByEmail(email, true)) {

            UserModel user = this.userRepository.getUserByEmail(email);

            this.passwordRecoverHandler.sendEmail(user.getUserId(), email);

            return true;
        }

        return false;
    }
}
