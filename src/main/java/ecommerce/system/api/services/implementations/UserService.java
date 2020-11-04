package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.enums.RolesEnum;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.exceptions.InactiveAccountException;
import ecommerce.system.api.exceptions.InvalidTokenException;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IUserService;
import ecommerce.system.api.tools.PasswordRecoverHandler;
import ecommerce.system.api.tools.SHAEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IAuthenticationService authenticationService;
    private final IUserRepository userRepository;
    private final SHAEncoder shaEncoder;
    private final PasswordRecoverHandler passwordRecoverHandler;

    @Autowired
    public UserService(IAuthenticationService authenticationService,
                       IUserRepository userRepository,
                       SHAEncoder shaEncoder,
                       PasswordRecoverHandler passwordRecoverHandler) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.shaEncoder = shaEncoder;
        this.passwordRecoverHandler = passwordRecoverHandler;
    }

    @Override
    public void createUser(UserModel user) throws NoSuchAlgorithmException, InvalidOperationException, InactiveAccountException {

        String encodedPassword = this.shaEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreationDate(LocalDateTime.now());
        user.setLastUpdate(null);
        user.setVerifiedEmail(true);
        user.setActive(true);

        UserModel checkedUser = this.userRepository.getUserByDocumentNumber(user.getDocumentNumber());

        if (checkedUser == null) {
            checkedUser = this.userRepository.getUserByEmail(user.getEmail());

            if (checkedUser != null) {

                if (!checkedUser.isActive()) {
                    checkedUser.setEmail(checkedUser.getEmail() + " [Inactive]");
                    this.userRepository.update(checkedUser);

                } else {
                    throw new InvalidOperationException("Já existe um usuário cadastrado com o e-mail informado.");
                }
            }

            this.userRepository.create(user);

        } else {

            if (checkedUser.isActive()) {
                throw new InvalidOperationException("Já existe um usuário cadastrado com o número do documento informado.");

            } else {
                throw new InactiveAccountException("Encontramos um cadastro inativo para o documento informado.");
            }
        }
    }

    @Override
    public void createCustomer(UserModel user)
            throws InvalidOperationException, NoSuchAlgorithmException, InactiveAccountException {

        String userRole = RolesEnum.getRoleById(user.getRoleId());

        if (userRole == null || (!userRole.equals("customer") && !userRole.equals("store_admin"))) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        this.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() {

        return this.userRepository.getAll();
    }

    @Override
    public List<UserModel> getUsersByRoleId(int roleId) {

        return this.userRepository.getUsersByRoleId(roleId);
    }

    @Override
    public UserModel getUserById(int id) {

        return this.userRepository.getById(id);
    }

    @Override
    public UserModel getUserByEmail(String email) {

        UserModel user = this.userRepository.getUserByEmail(email);

        if (user != null) {
            return user.isActive() ? user : null;
        }

        return null;
    }

    @Override
    public UserModel getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = this.getUserByEmail(email);

        return user;
    }

    @Override
    public boolean checkPasswordRecoverToken(String token) throws Exception {

        return this.passwordRecoverHandler.validateToken(token);
    }

    @Override
    public boolean sendPasswordRecoverEmail(String email) throws Exception {

        UserModel user = this.getUserByEmail(email);

        if (user != null) {

            this.passwordRecoverHandler.sendEmail(user.getUserId(), user.getEmail());

            return true;
        }

        return false;
    }

    @Override
    public void recoverPassword(String password, String token) throws Exception {

        if (this.checkPasswordRecoverToken(token)) {

            int userId = this.passwordRecoverHandler.extractId(token);

            this.updateUserPassword(true, userId, password);

        } else {
            throw new InvalidTokenException("Token expirado");
        }
    }

    @Override
    public void updateUserPassword(boolean isRecover, int userId, String password)
            throws InvalidOperationException, NoSuchAlgorithmException {

        if (!isRecover) {
            if (!this.authenticationService.isLoggedUser(userId)) {
                throw new InvalidOperationException("Operação não autorizada");
            }
        }

        UserModel user = this.userRepository.getById(userId);
        String encodedPassword = this.shaEncoder.encode(password);

        if (user.getPassword().equals(encodedPassword)) {
            throw new InvalidOperationException("Nova senha não pode ser igual a anterior");
        }

        user.setPassword(encodedPassword);
        user.setLastUpdate(LocalDateTime.now());

        this.userRepository.update(user);
    }

    @Override
    public void updateUserProfile(UserModel user) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(user.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        UserModel oldUser = this.getUserById(user.getUserId());

        user.setRoleId(oldUser.getRoleId());
        user.setPassword(oldUser.getPassword());
        user.setCreationDate(oldUser.getCreationDate());
        user.setVerifiedEmail(oldUser.isVerifiedEmail());
        user.setActive(oldUser.isActive());
        user.setLastUpdate(LocalDateTime.now());

        this.userRepository.update(user);
    }

    @Override
    public void deleteUserProfile(int id) throws InvalidOperationException, BatchUpdateException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = this.getUserByEmail(email);

        if (id != user.getUserId()) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);

        this.userRepository.delete(ids);
    }

    @Override
    public void deleteUsers(List<Integer> ids) throws BatchUpdateException {

        this.userRepository.delete(ids);
    }
}
