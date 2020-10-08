package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.exceptions.ForbiddenException;
import ecommerce.system.api.exceptions.InactiveAccountException;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.models.UserOptionModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {

    void createUser(UserModel user) throws NoSuchAlgorithmException, ForbiddenException, InactiveAccountException;
    void createCustomer(UserModel user) throws ForbiddenException, NoSuchAlgorithmException, InactiveAccountException;
    void createStoreAdmin(UserModel user) throws ForbiddenException, NoSuchAlgorithmException, InactiveAccountException;
    List<UserModel> getAllUsers() throws EmptySearchException;
    UserModel getUserById(int id);
    UserModel getUserByEmail(String email);
    List<UserOptionModel> getUserOptionsByRoleId(int roleId) throws EmptySearchException;
    UserModel getUserProfile() throws EmptySearchException;
    boolean sendPasswordRecoverEmail(String email) throws Exception;
    boolean checkPasswordRecoverToken(String token) throws Exception;
    void recoverPassword(String password, String token) throws Exception;
    void updateUser(UserModel user);
    void updateUserPassword(boolean isRecover, int userId, int roleId, String email, String password) throws ForbiddenException, NoSuchAlgorithmException;
    void deleteUserProfile(int id) throws ForbiddenException, BatchUpdateException;
    void deleteUsers(List<Integer> ids) throws BatchUpdateException;
}
