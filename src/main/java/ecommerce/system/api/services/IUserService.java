package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {

    void createUser(UserModel user) throws NoSuchAlgorithmException, InvalidOperationException;
    void createCustomer(UserModel user) throws InvalidOperationException, NoSuchAlgorithmException;
    void createProfileImage(MultipartFile file, int userId) throws InvalidOperationException, IOException;
    List<UserModel> getAllUsers();
    List<UserModel> getUsersByRoleId(int roleId);
    List<UserModel> getUsersByStoreId(int storeId);
    UserModel getUserById(int id);
    UserModel getUserByEmail(String email);
    UserModel getUserProfile();
    String getUserProfileImage(int userId, String path) throws InvalidOperationException, IOException;
    boolean checkPasswordRecoverToken(String token) throws Exception;
    boolean sendPasswordRecoverEmail(String email) throws Exception;
    void recoverPassword(String password, String token) throws Exception;
    void updateUserPassword(boolean isRecover, int userId, String password) throws InvalidOperationException, NoSuchAlgorithmException;
    void updateUserProfile(UserModel user) throws InvalidOperationException;
    void deleteUserProfile(int id) throws InvalidOperationException;
    void deleteUsers(List<Integer> ids) throws InvalidOperationException;
}
