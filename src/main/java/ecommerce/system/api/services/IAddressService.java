package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.AddressModel;

import java.util.ArrayList;

public interface IAddressService {

    public void createAddress(AddressModel address);
    public ArrayList<AddressModel> getAllAdresses() throws EmptySearchException;
    public ArrayList<AddressModel> getAdressesByUserId(int userId) throws EmptySearchException;
    public AddressModel getAdressById(int addressId);
    public void updateAddress(AddressModel address);
    public void deleteAdresses(ArrayList<Integer> ids) throws BatchUpdateException;
}
