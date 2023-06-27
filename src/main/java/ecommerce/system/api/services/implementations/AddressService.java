package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.Address;
import ecommerce.system.api.models.Store;
import ecommerce.system.api.repositories.IAddressRepository;
import ecommerce.system.api.services.IAddressService;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final IAuthenticationService authenticationService;
    private final IAddressRepository addressRepository;
    private final IStoreService storeService;

    @Autowired
    public AddressService(IAuthenticationService authenticationService, IAddressRepository addressRepository,
            IStoreService storeService) {
        this.authenticationService = authenticationService;
        this.addressRepository = addressRepository;
        this.storeService = storeService;
    }

    @Override
    public int createAddress(Address address, boolean relateWithUser) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        address.setCreationDate(LocalDateTime.now());
        address.setLastUpdate(null);
        address.setActive(true);

        int addressId = this.addressRepository.create(address);

        if (relateWithUser) {
            this.addressRepository.relateAddressAndUser(address.getUserId(), addressId);
        }

        return addressId;
    }

    @Override
    public List<Address> getAdressesByUserId(int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId) && this.authenticationService.isNotSystemAdmin()) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        return this.addressRepository.getAddressesByUserId(userId);
    }

    @Override
    public Address getAdressById(int adddressId) {

        return this.addressRepository.getById(adddressId);
    }

    @Override
    public void updateAddress(Address address) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        // HARDCODED
        address.setLastUpdate(LocalDateTime.now());

        if (!this.addressRepository.update(address)) {
            throw new InvalidOperationException("Endereço não encontrado!");
        }
    }

    @Override
    public void deleteAdress(int addressId) throws InvalidOperationException, IOException {

        Address address = this.getAdressById(addressId);

        if (address == null) {
            throw new InvalidOperationException("Endereço não encontrado!");
        }

        if (!this.authenticationService.isLoggedUser(address.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        List<Store> stores = this.storeService.getStoresByUserId(address.getUserId());

        if (stores != null) {
            for (Store store : stores) {

                if (store.getAddressId() == addressId) {
                    throw new InvalidOperationException("Não é possível deletar um endereço associado a uma loja.");
                }
            }
        }

        this.addressRepository.delete(addressId);
    }
}
