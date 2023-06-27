package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.Telephone;

import java.io.IOException;
import java.util.List;

public interface ITelephoneService {

    int createTelephone(Telephone telephone, boolean relateWithUser) throws InvalidOperationException;

    List<Telephone> getTelephonesByUserId(int userId) throws InvalidOperationException;

    Telephone getTelephoneById(int telephoneId);

    void updateTelephone(Telephone telephone) throws InvalidOperationException;

    void deleteTelephone(int telephoneId) throws InvalidOperationException, IOException;
}
