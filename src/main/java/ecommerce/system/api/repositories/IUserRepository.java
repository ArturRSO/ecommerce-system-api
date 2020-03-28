package ecommerce.system.api.repositories;

import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;

public interface IUserRepository extends IBaseRepository<UserModel> {
    public UserModel getUserByEmail(String email);
    public boolean checkUserCredentials(CredentialsModel credentials);
    public boolean checkUserByEmail(String email, boolean isActive);
}
