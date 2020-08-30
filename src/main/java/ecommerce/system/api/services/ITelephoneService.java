package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.TelephoneModel;

import java.util.ArrayList;

public interface ITelephoneService {

    public void createTelephone(TelephoneModel telephone);
    public ArrayList<TelephoneModel> getAllTelephones() throws EmptySearchException;
    public ArrayList<TelephoneModel> getTelephonesByUserId(int userId) throws EmptySearchException;
    public TelephoneModel getTelephoneById(int telephoneId);
    public void updateTelephone(TelephoneModel telephone);
    public void deleteTelephones(ArrayList<Integer> ids) throws BatchUpdateException;
}
