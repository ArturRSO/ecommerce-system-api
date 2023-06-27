package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.Address;

import java.io.IOException;
import java.util.List;

public interface IAddressService {

    int createAddress(Address address, boolean relateWithUser) throws InvalidOperationException;

    List<Address> getAdressesByUserId(int userId) throws InvalidOperationException;

    Address getAdressById(int addressId);

    void updateAddress(Address address) throws InvalidOperationException;

    void deleteAdress(int addressId) throws InvalidOperationException, IOException;
}
