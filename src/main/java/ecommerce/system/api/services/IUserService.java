package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {

    int createUser(UserModel user) throws NoSuchAlgorithmException, InvalidOperationException;
    void createProfileImage(MultipartFile file, int userId) throws InvalidOperationException, IOException;
    List<UserModel> getAllUsers() throws IOException;
    List<UserModel> getUsersByRoleId(int roleId) throws IOException;
    List<UserModel> getUsersByStoreId(int storeId) throws IOException;
    UserModel getUserById(int id, boolean imagePath) throws IOException;
    UserModel getUserByEmail(String email);
    UserModel getUserProfile() throws IOException;
    boolean checkPasswordRecoverToken(String token) throws Exception;
    boolean sendPasswordRecoverEmail(String email) throws Exception;
    void recoverPassword(String password, String token) throws Exception;
    void updateUserPassword(boolean isRecover, int userId, String password) throws InvalidOperationException, NoSuchAlgorithmException;
    void updateUser(UserModel user) throws InvalidOperationException, IOException;
    void deleteUser(int userId) throws InvalidOperationException, IOException;
}
