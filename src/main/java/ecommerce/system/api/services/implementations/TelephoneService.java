package ecommerce.system.api.services.implementations;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.repositories.ITelephoneRepository;
import ecommerce.system.api.services.ITelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelephoneService implements ITelephoneService {

    private final ITelephoneRepository telephoneRepository;

    @Autowired
    public TelephoneService(ITelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    @Override
    public void createTelephone(TelephoneModel telephone) {

        telephone.setCreationDate(LocalDateTime.now());
        telephone.setLastUpdate(null);
        telephone.setActive(true);

        this.telephoneRepository.create(telephone);
    }

    @Override
    public List<TelephoneModel> getAllTelephones() throws EmptySearchException {

        return this.telephoneRepository.getAll();
    }

    @Override
    public List<TelephoneModel> getTelephonesByUserId(int userId) throws EmptySearchException {

        return this.telephoneRepository.getTelephonesByUserId(userId);
    }

    @Override
    public TelephoneModel getTelephoneById(int telephoneId) {

        return this.telephoneRepository.getById(telephoneId);
    }

    @Override
    public void updateTelephone(TelephoneModel telephone) {

        telephone.setLastUpdate(LocalDateTime.now());

        this.telephoneRepository.update(telephone);
    }

    @Override
    public void deleteTelephones(List<Integer> ids) throws BatchUpdateException {

        this.telephoneRepository.delete(ids);
    }
}
