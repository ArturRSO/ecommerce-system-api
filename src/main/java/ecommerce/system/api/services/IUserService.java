package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {

    int createUser(User user) throws NoSuchAlgorithmException, InvalidOperationException;

    void createProfileImage(MultipartFile file, int userId) throws InvalidOperationException, IOException;

    List<User> getAllUsers() throws IOException;

    List<User> getUsersByRoleId(int roleId) throws IOException;

    List<User> getUsersByStoreId(int storeId) throws IOException;

    User getUserById(int id, boolean imagePath) throws IOException;

    User getUserByEmail(String email);

    User getUserProfile() throws IOException;

    boolean checkPasswordRecoverToken(String token) throws Exception;

    boolean sendPasswordRecoverEmail(String email) throws Exception;

    void recoverPassword(String password, String token) throws Exception;

    void updateUserPassword(boolean isRecover, int userId, String password)
            throws InvalidOperationException, NoSuchAlgorithmException;

    void updateUser(User user) throws InvalidOperationException, IOException;

    void deleteUser(int userId) throws InvalidOperationException, IOException;
}
