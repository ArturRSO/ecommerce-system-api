package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.exceptions.ForbiddenException;
import ecommerce.system.api.models.UserModel;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface IUserService {

    public void createUser(UserModel user) throws NoSuchAlgorithmException;
    public void createCustomer(UserModel user) throws ForbiddenException, NoSuchAlgorithmException;
    public ArrayList<UserModel> getAllUsers() throws EmptySearchException;
    public UserModel getUserById(int id);
    public UserModel getUserByEmail(String email);
    public UserModel getProfile();
    public void updateUser(UserModel user) throws EmptySearchException;
    public void deleteUsers(ArrayList<Integer> ids) throws BatchUpdateException;
}
