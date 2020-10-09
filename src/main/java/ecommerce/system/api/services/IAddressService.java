package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.models.AddressModel;

import java.util.List;

public interface IAddressService {

    void createAddress(AddressModel address);
    List<AddressModel> getAllAdresses();
    List<AddressModel> getAdressesByUserId(int userId);
    AddressModel getAdressById(int addressId);
    void updateAddress(AddressModel address);
    void deleteAdresses(List<Integer> ids) throws BatchUpdateException;
}
