package ecommerce.system.api.repositories;

import ecommerce.system.api.models.TelephoneModel;

import java.util.List;

public interface ITelephoneRepository extends ICrudRepository<TelephoneModel> {

    void relateTelephoneAndUser(int userId, int telephoneId);
    List<TelephoneModel> getTelephonesByUserId(int userId);
}
