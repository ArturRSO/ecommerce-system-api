package ecommerce.system.api.repositories;

import ecommerce.system.api.models.Telephone;

import java.util.List;

public interface ITelephoneRepository extends ICrudRepository<Telephone> {

    void relateTelephoneAndUser(int userId, int telephoneId);

    List<Telephone> getTelephonesByUserId(int userId);
}
