package ecommerce.system.api.repositories;

import ecommerce.system.api.models.TelephoneModel;

import java.util.List;

public interface ITelephoneRepository extends IBaseRepository<TelephoneModel> {

    List<TelephoneModel> getTelephonesByUserId(int userId);
}
