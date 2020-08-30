package ecommerce.system.api.repositories;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.AddressModel;

import java.util.ArrayList;

public interface IAddressRepository extends IBaseRepository<AddressModel> {

    public ArrayList<AddressModel> getAddressesByUserId(int userId) throws EmptySearchException;
}
