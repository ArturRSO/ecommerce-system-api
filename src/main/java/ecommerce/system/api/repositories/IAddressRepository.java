package ecommerce.system.api.repositories;

import ecommerce.system.api.models.AddressModel;

import java.util.List;

public interface IAddressRepository extends ICrudRepository<AddressModel> {

    void relateAddressAndUser(int userId, int adddressId);
    List<AddressModel> getAddressesByUserId(int userId);
}
