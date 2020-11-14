package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.enums.NotificationsEnum;
import ecommerce.system.api.enums.RolesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.exceptions.InvalidTokenException;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IFileService;
import ecommerce.system.api.services.IUserService;
import ecommerce.system.api.tools.NotificationHandler;
import ecommerce.system.api.tools.SHAEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IAuthenticationService authenticationService;
    private final IFileService fileService;
    private final IUserRepository userRepository;
    private final SHAEncoder shaEncoder;
    private final NotificationHandler notificationHandler;

    @Autowired
    public UserService(IAuthenticationService authenticationService,
                       IFileService fileService,
                       IUserRepository userRepository,
                       SHAEncoder shaEncoder,
                       NotificationHandler notificationHandler) {
        this.authenticationService = authenticationService;
        this.fileService = fileService;
        this.userRepository = userRepository;
        this.shaEncoder = shaEncoder;
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void createUser(UserModel user) throws NoSuchAlgorithmException, InvalidOperationException {

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

            } else if (checkedUser.getEmail().equals(user.getEmail())) {
                throw new InvalidOperationException("Já existe um usuário cadastrado com o e-mail informado.");

            } else {
                checkedUser.setDocumentNumber(checkedUser.getDocumentNumber() + " [Inactive]");
                this.userRepository.update(checkedUser);

                this.userRepository.create(user);
            }
        }
    }

    @Override
    public void createCustomer(UserModel user)
            throws InvalidOperationException, NoSuchAlgorithmException {

        String userRole = RolesEnum.getRoleById(user.getRoleId());

        if (userRole == null || (!userRole.equals("customer") && !userRole.equals("store_admin"))) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        this.createUser(user);
    }

    @Override
    public void createProfileImage(MultipartFile file, int userId) throws InvalidOperationException, IOException {

        if (!this.authenticationService.isLoggedUser(userId)) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        String imagePath = this.fileService.saveMultpartImage(file, "user", userId);

        UserModel user = this.userRepository.getById(userId);
        user.setProfileImagePath(imagePath);
        user.setLastUpdate(LocalDateTime.now());

        if (!this.userRepository.update(user)) {
            throw new InvalidOperationException("Usuário não encontrado!");
        }
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
    public List<UserModel> getUsersByStoreId(int storeId) {

        return this.userRepository.getUsersByStoreId(storeId);
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

        return this.getUserByEmail(email);
    }

    @Override
    public String getUserProfileImage(int userId, String path) throws InvalidOperationException, IOException {

        if (!this.authenticationService.isLoggedUser(userId)) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        return this.fileService.getImageBase64(UriUtils.decode(path, "UTF-8"));
    }

    @Override
    public boolean checkPasswordRecoverToken(String token) throws Exception {

        return this.notificationHandler.validateToken(token, NotificationsEnum.PASSWORD_RECOVER);
    }

    @Override
    public boolean sendPasswordRecoverEmail(String email) throws Exception {

        UserModel user = this.getUserByEmail(email);

        if (user != null) {

            this.notificationHandler.sendEmail(user.getUserId(), user.getEmail(), NotificationsEnum.PASSWORD_RECOVER, null);

            return true;
        }

        return false;
    }

    @Override
    public void recoverPassword(String password, String token) throws Exception {

        if (this.checkPasswordRecoverToken(token)) {

            int userId = this.notificationHandler.extractUserId(token, NotificationsEnum.PASSWORD_RECOVER);

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
                throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
            }
        }

        UserModel user = this.userRepository.getById(userId);

        if (user == null) {
            throw new InvalidOperationException("Usuário não encontrado!");
        }

        String encodedPassword = this.shaEncoder.encode(password);

        if (user.getPassword().equals(encodedPassword)) {
            throw new InvalidOperationException("Nova senha não pode ser igual a anterior!");
        }

        user.setPassword(encodedPassword);
        user.setLastUpdate(LocalDateTime.now());

        if (!this.userRepository.update(user)) {
            throw new InvalidOperationException("Usuário não encontrado!");
        }
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

        if (!this.userRepository.update(user)) {
            throw new InvalidOperationException("Usuário não encontrado!");
        }
    }

    @Override
    public void deleteUserProfile(int id) throws InvalidOperationException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserModel user = this.getUserByEmail(email);

        if (id != user.getUserId()) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        this.userRepository.delete(id);
    }

    @Override
    public void deleteUsers(List<Integer> ids) throws InvalidOperationException {

        int deletionCount = 0;

        for (int id : ids) {

            if (this.userRepository.delete(id)) {
                deletionCount++;
            }
        }

        if (ids.size() != deletionCount) {

            int deletionFails = ids.size() - deletionCount;

            throw new InvalidOperationException("Erro: " + deletionCount + " usuário(s) deletado(s), " + deletionFails + " usuário(s) não encontrado(s).");
        }
    }
}
