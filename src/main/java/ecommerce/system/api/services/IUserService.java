package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.exceptions.ForbiddenException;
import ecommerce.system.api.models.UserModel;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface IUserService {

    public void createUser(UserModel user) throws NoSuchAlgorithmException, EmptySearchException;
    public void createCustomer(UserModel user) throws ForbiddenException, NoSuchAlgorithmException, EmptySearchException;
    public ArrayList<UserModel> getAllUsers() throws EmptySearchException;
    public UserModel getUserById(int id);
    public UserModel getUserByEmail(String email);
    public UserModel getProfile() throws EmptySearchException;
    public boolean sendPasswordRecoverEmail(String email) throws Exception;
    public boolean checkPasswordRecoverToken(String token) throws Exception;
    public void recoverPassword(String password, String token) throws Exception;
    public void updateUser(UserModel user) throws EmptySearchException;
    public void updateUserPassword(boolean isRecover, int userId, int roleId, String email, String password) throws ForbiddenException, NoSuchAlgorithmException;
    public void deleteUserProfile(int id) throws ForbiddenException, BatchUpdateException;
    public void deleteUsers(ArrayList<Integer> ids) throws BatchUpdateException;
}
