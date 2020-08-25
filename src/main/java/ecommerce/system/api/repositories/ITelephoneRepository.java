package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.TelephoneModel;

import java.util.ArrayList;

public interface ITelephoneRepository extends IBaseRepository<TelephoneModel> {

    public ArrayList<TelephoneModel> getTelephoneByUserId(int userId) throws EmptySearchException;
}
