package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.RolesEnum;
import ecommerce.system.api.exceptions.*;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.models.UserOptionModel;
import ecommerce.system.api.repositories.IUserOptionRepository;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IUserService;
import ecommerce.system.api.tools.PasswordRecoverHandler;
import ecommerce.system.api.tools.SHAEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IUserRepository userRepository;
    private final IUserOptionRepository userOptionRepository;
    private final SHAEncoder shaEncoder;
    private final PasswordRecoverHandler passwordRecoverHandler;

    @Value("${images.path.users.default}")
    private String defaultProfileImagePath;

    @Autowired
    public UserService(IUserRepository userRepository,
                       IUserOptionRepository userOptionRepository, SHAEncoder shaEncoder,
                       PasswordRecoverHandler passwordRecoverHandler) {
        this.userRepository = userRepository;
        this.userOptionRepository = userOptionRepository;
        this.shaEncoder = shaEncoder;
        this.passwordRecoverHandler = passwordRecoverHandler;
    }

    @Override
    public void createUser(UserModel user) throws NoSuchAlgorithmException, ForbiddenException, InactiveAccountException {

        String encodedPassword = this.shaEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreationDate(LocalDateTime.now());
        user.setLastUpdate(null);
        user.setActive(true);

        if (user.getProfileImagePath() == null) {
            user.setProfileImagePath(this.defaultProfileImagePath);
        }

        UserModel checkedUser = this.userRepository.getUserByDocumentNumber(user.getDocumentNumber());

        if (checkedUser == null) {
            checkedUser = this.userRepository.getUserByEmail(user.getEmail());

            if (checkedUser != null) {

                checkedUser.setEmail(checkedUser.getEmail() + " [Inactive]");
                this.userRepository.update(checkedUser);
            }

            this.userRepository.create(user);

        } else {

            if (checkedUser.isActive()) {
                throw new ForbiddenException("Já existe um usuário cadastrado com o número do documento informado.");

            } else {
                throw new InactiveAccountException("Encontramos um cadastro inativo para o documento informado.");
            }
        }
    }

    @Override
    public void createCustomer(UserModel user)
            throws ForbiddenException, NoSuchAlgorithmException, InactiveAccountException {

        String userRole = RolesEnum.getRoleById(user.getRoleId());

        if (userRole == null || !userRole.equals("customer")) {
            throw new ForbiddenException("Operação não permitida!");
        }

        this.createUser(user);
    }

    @Override
    public void createStoreAdmin(UserModel user)
            throws ForbiddenException, NoSuchAlgorithmException, InactiveAccountException {

        String userRole = RolesEnum.getRoleById(user.getRoleId());

        if (userRole == null || !userRole.equals("store_admin")) {
            throw new ForbiddenException("Operação não permitida!");
        }

        this.createUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() throws EmptySearchException {

        return this.userRepository.getAll();
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
    public List<UserOptionModel> getUserOptionsByRoleId(int roleId) throws EmptySearchException {
        return this.userOptionRepository.getUserOptionsByRoleId(roleId);
    }

    @Override
    public UserModel getUserProfile() throws EmptySearchException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = this.getUserByEmail(email);

        user.setOptions(this.getUserOptionsByRoleId(user.getRoleId()));

        return user;
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
    public boolean checkPasswordRecoverToken(String token) throws Exception {

        return this.passwordRecoverHandler.validateToken(token);
    }

    @Override
    public void recoverPassword(String password, String token)
            throws Exception {

        if (this.checkPasswordRecoverToken(token)) {

            int userId = this.passwordRecoverHandler.extractId(token);
            UserModel user = this.userRepository.getById(userId);
            String encodedPassword = this.shaEncoder.encode(password);

            if (user.getPassword().equals(encodedPassword)) {

               throw new ForbiddenException("Nova senha não pode ser igual a anterior!");

            } else {
               this.updateUserPassword(true, user.getUserId(), user.getRoleId(), user.getEmail(), password);
            }

        } else {
            throw new InvalidTokenException("Token expirado");
        }
    }

    @Override
    public void updateUser(UserModel user) {

        UserModel oldUser = this.userRepository.getById(user.getUserId());

        user.setPassword(oldUser.getPassword());
        user.setCreationDate(oldUser.getCreationDate());
        user.setActive(oldUser.isActive());
        user.setLastUpdate(LocalDateTime.now());

        this.userRepository.update(user);
    }

    @Override
    public void updateUserPassword(boolean isRecover, int userId, int roleId, String email, String password)
            throws ForbiddenException, NoSuchAlgorithmException {

        String role = RolesEnum.getRoleById(roleId);
        UserModel user = this.userRepository.getById(userId);
        String loggedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (role != null && (role.equals("store_employee") || role.equals("customer"))) {

            if (!(user.getEmail().equals(loggedEmail))) {
                throw new ForbiddenException("Operação não autorizada");
            }
        }

        // TO DO - Flux to store_admin role

        String encodedPassword = this.shaEncoder.encode(password);

        user.setPassword(encodedPassword);
        user.setLastUpdate(LocalDateTime.now());

        this.userRepository.update(user);
    }

    @Override
    public void deleteUserProfile(int id) throws ForbiddenException, BatchUpdateException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = this.getUserByEmail(email);

        if (id != user.getUserId()) {
            throw new ForbiddenException("Operação não permitida!");
        }

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);

        this.userRepository.delete(ids);
    }

    @Override
    public void deleteUsers(List<Integer> ids) throws BatchUpdateException {

        logger.info("Deleting " + ids.size() + " users...");

        (ids).forEach((id) -> {
            UserModel user = this.userRepository.getById(id);
            logger.info("User " + user.getUserId() + " found.");
        });

        this.userRepository.delete(ids);
    }
}
