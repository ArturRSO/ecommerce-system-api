package ecommerce.system.api.services.implementations;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.models.AddressModel;
import ecommerce.system.api.repositories.IAddressRepository;
import ecommerce.system.api.services.IAddressService;
import ecommerce.system.api.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final IAuthenticationService authenticationService;
    private final IAddressRepository addressRepository;

    @Autowired
    public AddressService(IAuthenticationService authenticationService, IAddressRepository addressRepository) {
        this.authenticationService = authenticationService;
        this.addressRepository = addressRepository;
    }

    @Override
    public void createAddress(AddressModel address) {

        address.setCreationDate(LocalDateTime.now());
        address.setLastUpdate(null);
        address.setActive(true);

        this.addressRepository.create(address);
    }

    @Override
    public List<AddressModel> getAllAdresses() {

        return this.addressRepository.getAll();
    }

    @Override
    public List<AddressModel> getAdressesByUserId(int userId) {

        return this.addressRepository.getAddressesByUserId(userId);
    }

    @Override
    public AddressModel getAdressById(int adddressId) {

        return this.addressRepository.getById(adddressId);
    }

    @Override
    public void updateAddress(AddressModel address) {

        address.setLastUpdate(LocalDateTime.now());

        this.addressRepository.update(address);
    }

    @Override
    public void deleteAdresses(List<Integer> ids) throws BatchUpdateException {

        this.addressRepository.delete(ids);
    }
}
