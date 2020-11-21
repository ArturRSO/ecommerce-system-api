package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.AddressModel;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.repositories.IAddressRepository;
import ecommerce.system.api.services.IAddressService;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final IAuthenticationService authenticationService;
    private final IAddressRepository addressRepository;
    private final IStoreService storeService;

    @Autowired
    public AddressService(IAuthenticationService authenticationService, IAddressRepository addressRepository, IStoreService storeService) {
        this.authenticationService = authenticationService;
        this.addressRepository = addressRepository;
        this.storeService = storeService;
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
    public List<AddressModel> getAdressesByUserId(int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId) || !this.authenticationService.isSystemAdmin()) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        return this.addressRepository.getAddressesByUserId(userId);
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

        if (!this.addressRepository.update(address)) {
            throw new InvalidOperationException("Endereço não encontrado!");
        }
    }

    @Override
    public void deleteAdress(int addressId) throws InvalidOperationException {

        AddressModel address = this.getAdressById(addressId);

        if (address == null) {
            throw new InvalidOperationException("Endereço não encontrado!");
        }

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        List<StoreModel> stores = this.storeService.getStoresByUserId(address.getUserId());

        for (StoreModel store : stores) {

            if (store.getAddressId() == addressId) {
                throw new InvalidOperationException("Não é possível deletar um endereço associado a uma loja.");
            }
        }

        this.addressRepository.delete(addressId);
    }
}
