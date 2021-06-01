package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.repositories.ITelephoneRepository;
import ecommerce.system.api.services.IAuthenticationService;
import ecommerce.system.api.services.IStoreService;
import ecommerce.system.api.services.ITelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelephoneService implements ITelephoneService {

    private final IAuthenticationService authenticationService;
    private final ITelephoneRepository telephoneRepository;
    private final IStoreService storeService;

    @Autowired
    public TelephoneService(IAuthenticationService authenticationService, ITelephoneRepository telephoneRepository, IStoreService storeService) {
        this.authenticationService = authenticationService;
        this.telephoneRepository = telephoneRepository;
        this.storeService = storeService;
    }

    @Override
    public void createTelephone(TelephoneModel telephone) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(telephone.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        // HARDCODED
        telephone.setInternationalCode("+55");
        telephone.setCreationDate(LocalDateTime.now());
        telephone.setLastUpdate(null);
        telephone.setActive(true);

        int telephoneId = this.telephoneRepository.create(telephone);
        this.telephoneRepository.relateTelephoneAndUser(telephone.getUserId(), telephoneId);
    }

    @Override
    public List<TelephoneModel> getTelephonesByUserId(int userId) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(userId) && this.authenticationService.isNotSystemAdmin()) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        return this.telephoneRepository.getTelephonesByUserId(userId);
    }

    @Override
    public TelephoneModel getTelephoneById(int telephoneId) {

        return this.telephoneRepository.getById(telephoneId);
    }

    @Override
    public void updateTelephone(TelephoneModel telephone) throws InvalidOperationException {

        if (!this.authenticationService.isLoggedUser(telephone.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        // HARDCODED
        telephone.setInternationalCode("+55");
        telephone.setLastUpdate(LocalDateTime.now());

        if (!this.telephoneRepository.update(telephone)) {
            throw new InvalidOperationException("Telefone não encontrado!");
        }
    }

    @Override
    public void deleteTelephone(int telephoneId) throws InvalidOperationException {

        TelephoneModel telephone = this.getTelephoneById(telephoneId);

        if (telephone == null) {
            throw new InvalidOperationException("Telefone não encontrado!");
        }

        if (!this.authenticationService.isLoggedUser(telephone.getUserId())) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        List<StoreModel> stores = this.storeService.getStoresByUserId(telephone.getUserId());

        if (stores != null) {
            for (StoreModel store : stores) {
                if (store.getTelephoneId() == telephoneId) {
                    throw new InvalidOperationException("Não é possível deletar um endereço associado a uma loja.");
                }
            }
        }

        this.telephoneRepository.delete(telephoneId);
    }
}
