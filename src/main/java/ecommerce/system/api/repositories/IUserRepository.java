package ecommerce.system.api.repositories;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;

public interface IUserRepository extends IBaseRepository<UserModel> {
    UserModel getUserByDocumentNumber(String document);
    UserModel getUserByEmail(String email);
    boolean checkUserCredentials(CredentialsModel credentials);
}
