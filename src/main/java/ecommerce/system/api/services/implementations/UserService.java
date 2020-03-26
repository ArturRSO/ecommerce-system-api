package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.RolesEnum;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.exceptions.ForbiddenException;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
import ecommerce.system.api.services.IUserService;
import ecommerce.system.api.tools.SHAEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    private final IUserRepository<UserModel> userRepository;
    private final SHAEncoder shaEncoder;

    @Value("${images.path.users.default}")
    private String defaultProfileImagePath;

    @Autowired
    public UserService(IUserRepository<UserModel> userRepository, SHAEncoder shaEncoder) {
        this.userRepository = userRepository;
        this.shaEncoder = shaEncoder;
    }

    @Override
    public void createUser(UserModel user) throws NoSuchAlgorithmException {

        String encodedPassword = this.shaEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreationDate(LocalDateTime.now());
        user.setLastUpdate(null);
        user.setActive(true);

        if (user.getProfileImagePath() == null) {
            user.setProfileImagePath(this.defaultProfileImagePath);
        }

        this.userRepository.create(user);
    }

    @Override
    public void createCustomer(UserModel user) throws ForbiddenException, NoSuchAlgorithmException {

        String userRole = RolesEnum.getRoleById(user.getRoleId());

        if(userRole != "customer") {
            throw new ForbiddenException("Operação não permitida!");
        }

        this.createUser(user);
    }

    @Override
    public ArrayList<UserModel> getAllUsers() throws EmptySearchException {

        return this.userRepository.getAll();
    }

    @Override
    public UserModel getUserById(int id) {

        return this.userRepository.getById(id);
    }

    @Override
    public UserModel getUserByEmail(String email) {

        return this.userRepository.getUserByEmail(email);
    }

    @Override
    public UserModel getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return this.getUserByEmail(email);
    }

    @Override
    public void updateUser(UserModel user) throws EmptySearchException {

        UserModel oldUser = this.userRepository.getById(user.getUserId());

        user.setPassword(oldUser.getPassword());
        user.setCreationDate(oldUser.getCreationDate());
        user.setActive(oldUser.isActive());
        user.setLastUpdate(LocalDateTime.now());

        this.userRepository.update(user);
    }

    @Override
    public void deleteUsers(ArrayList<Integer> ids) throws BatchUpdateException {

        System.out.println("Deleting " + ids.size() + " users...");

        (ids).forEach((id) -> {
            UserModel user = this.userRepository.getById(id);
            System.out.println("User " + user.getUserId() + " found.");
        });

        this.userRepository.delete(ids);
    }
}
