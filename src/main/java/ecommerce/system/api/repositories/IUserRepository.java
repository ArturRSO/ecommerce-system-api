package ecommerce.system.api.repositories;

import ecommerce.system.api.dto.CredentialsDTO;
import ecommerce.system.api.models.User;

import java.util.List;

public interface IUserRepository extends ICrudRepository<User> {

    List<User> getAllUsers();

    List<User> getUsersByRoleId(int roleId);

    List<User> getUsersByStoreId(int storeId);

    User getUserByDocumentNumber(String document);

    User getUserByEmail(String email);

    User getUserByCredentials(CredentialsDTO credentials);
}
