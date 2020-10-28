package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
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
    public void createAddress(AddressModel address) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

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
    public List<AddressModel> getProfileAdresses(int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId)) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        return this.getAdressesByUserId(userId);
    }

    @Override
    public AddressModel getAdressById(int adddressId) {

        return this.addressRepository.getById(adddressId);
    }

    @Override
    public void updateAddress(AddressModel address) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        address.setLastUpdate(LocalDateTime.now());
        this.addressRepository.update(address);
    }

    @Override
    public void deleteAdresses(List<Integer> ids) throws BatchUpdateException, InvalidOperationException {

        for (int id : ids) {
            AddressModel address = this.getAdressById(id);

            if (!this.authenticationService.isLoggedUser(address.getUserId())) {
                throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
            }
        }

        this.addressRepository.delete(ids);
    }
}
