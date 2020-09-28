package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.AddressModel;

import java.util.List;

public interface IAddressService {

    void createAddress(AddressModel address);
    List<AddressModel> getAllAdresses() throws EmptySearchException;
    List<AddressModel> getAdressesByUserId(int userId) throws EmptySearchException;
    AddressModel getAdressById(int addressId);
    void updateAddress(AddressModel address);
    void deleteAdresses(List<Integer> ids) throws BatchUpdateException;
}
