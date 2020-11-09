package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.TelephoneModel;

import java.util.List;

public interface ITelephoneService {

    void createTelephone(TelephoneModel telephone) throws InvalidOperationException;
    List<TelephoneModel> getAllTelephones();
    List<TelephoneModel> getTelephonesByUserId(int userId);
    List<TelephoneModel> getProfileTelephones(int userId) throws InvalidOperationException;
    TelephoneModel getTelephoneById(int telephoneId);
    void updateTelephone(TelephoneModel telephone) throws InvalidOperationException;
    void deleteTelephones(List<Integer> ids) throws InvalidOperationException;
}
