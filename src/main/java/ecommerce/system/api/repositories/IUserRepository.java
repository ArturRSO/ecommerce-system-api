package ecommerce.system.api.repositories;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;

import java.util.List;

public interface IUserRepository extends ICrudRepository<UserModel> {
    List<UserModel> getUsersByRoleId(int roleId);
    List<UserModel> getUsersByStoreId(int storeId);
    UserModel getUserByDocumentNumber(String document);
    UserModel getUserByEmail(String email);
    UserModel getUserByCredentials(CredentialsModel credentials);
}
