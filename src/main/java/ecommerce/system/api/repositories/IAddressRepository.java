package ecommerce.system.api.repositories;

import ecommerce.system.api.models.Address;

import java.util.List;

public interface IAddressRepository extends ICrudRepository<Address> {

    void relateAddressAndUser(int userId, int adddressId);

    List<Address> getAddressesByUserId(int userId);
}
