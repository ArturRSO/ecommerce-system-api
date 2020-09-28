package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.TelephoneModel;

import java.util.List;

public interface ITelephoneService {

    void createTelephone(TelephoneModel telephone);
    List<TelephoneModel> getAllTelephones() throws EmptySearchException;
    List<TelephoneModel> getTelephonesByUserId(int userId) throws EmptySearchException;
    TelephoneModel getTelephoneById(int telephoneId);
    void updateTelephone(TelephoneModel telephone);
    void deleteTelephones(List<Integer> ids) throws BatchUpdateException;
}
