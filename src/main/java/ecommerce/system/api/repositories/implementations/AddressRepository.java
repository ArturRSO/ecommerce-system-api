package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.AddressEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.models.AddressModel;
import ecommerce.system.api.repositories.IAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class AddressRepository implements IAddressRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(AddressModel object) {

        AddressEntity address = new AddressEntity(object);

        this.entityManager.persist(address);
    }

    @Override
    public List<AddressModel> getAll() {

        String query = "FROM AddressEntity a WHERE a.active = true ORDER BY a.addressId ASC";
        TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class);
        List<AddressEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<AddressModel> addresses = new ArrayList<>();
        (entities).forEach((address) -> addresses.add(address.toModel()));

        return addresses;
    }

    @Override
    public AddressModel getById(int id) {

        try {

            String query = "FROM AddressEntity a WHERE a.active = true AND a.addressId = :addressId";
            TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class)
                    .setParameter("addressId", id);
            AddressEntity address = result.getSingleResult();

            return address.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<AddressModel> getAddressesByUserId(int userId) {

        String query = "FROM AddressEntity a WHERE a.active = true AND a.userId = :userId ORDER BY a.addressId ASC";
        TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class)
                .setParameter("userId", userId);
        List<AddressEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<AddressModel> addresses = new ArrayList<>();
        (entities).forEach((address) -> addresses.add(address.toModel()));

        return addresses;
    }

    @Override
    public void update(AddressModel object) {

        AddressEntity updatedAddress = new AddressEntity(object);

        this.entityManager.merge(updatedAddress);
    }

    @Override
    public void delete(List<Integer> ids) throws BatchUpdateException {

        int result = 0;
        String query = "UPDATE AddressEntity SET active = false, lastUpdate = :date WHERE addressId = :addressId";

        for (int id : ids) {
            Query update = entityManager.createQuery(query)
                    .setParameter("addressId", id)
                    .setParameter("date", LocalDateTime.now());
            result += update.executeUpdate();
        }

        if (result != ids.size()) {
            int deleteFails = ids.size() - result;

            throw new BatchUpdateException("Erro ao deletar " + deleteFails + " endereço(s). Nenhum endereço deletado.");
        }
    }
}
