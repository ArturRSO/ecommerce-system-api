package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.AddressModel;

import java.util.List;

public interface IAddressService {

    void createAddress(AddressModel address) throws InvalidOperationException;
    List<AddressModel> getAllAdresses();
    List<AddressModel> getAdressesByUserId(int userId);
    List<AddressModel> getProfileAdresses(int userId) throws InvalidOperationException;
    AddressModel getAdressById(int addressId);
    void updateAddress(AddressModel address) throws InvalidOperationException;
    void deleteAdresses(List<Integer> ids) throws BatchUpdateException, InvalidOperationException;
}
