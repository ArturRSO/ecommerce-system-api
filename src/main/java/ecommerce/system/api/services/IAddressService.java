package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.AddressModel;

import java.io.IOException;
import java.util.List;

public interface IAddressService {

    int createAddress(AddressModel address) throws InvalidOperationException;
    List<AddressModel> getAdressesByUserId(int userId) throws InvalidOperationException;
    AddressModel getAdressById(int addressId);
    void updateAddress(AddressModel address) throws InvalidOperationException;
    void deleteAdress(int addressId) throws InvalidOperationException, IOException;
}
