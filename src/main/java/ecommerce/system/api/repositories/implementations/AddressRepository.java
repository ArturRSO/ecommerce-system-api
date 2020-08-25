package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.AddressEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.AddressModel;
import ecommerce.system.api.repositories.IAddressRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class AddressRepository implements IAddressRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(AddressModel object) {

        AddressEntity address = new AddressEntity(object);

        this.entityManager.persist(address);
    }

    @Override
    public ArrayList<AddressModel> getAll() throws EmptySearchException {

        String query = "FROM AddressEntity a WHERE a.isActive = true ORDER BY a.addressId ASC";
        TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class);
        List<AddressEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhum endereço encontrado!");
        }

        ArrayList<AddressModel> addresses = new ArrayList<>();
        (entities).forEach((address) -> {
            addresses.add(address.toModel());
        });

        return addresses;
    }

    @Override
    public AddressModel getById(int id) {

        String query = "FROM AddressEntity a WHERE a.isActive = true AND a.addressId = :addressId";
        TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class)
                .setParameter("addressId", id);
        AddressEntity address = result.getSingleResult();

        return address.toModel();
    }

    @Override
    public ArrayList<AddressModel> getAddressByUserId(int userId) throws EmptySearchException {

        String query = "FROM AddressEntity a WHERE a.isActive = true AND a.userId = :userId ORDER BY a.addressId ASC";
        TypedQuery<AddressEntity> result = this.entityManager.createQuery(query, AddressEntity.class)
                .setParameter("userId", userId);
        List<AddressEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhum endereço encontrado!");
        }

        ArrayList<AddressModel> addresses = new ArrayList<>();
        (entities).forEach((address) -> {
            addresses.add(address.toModel());
        });

        return addresses;
    }

    @Override
    public void update(AddressModel object) {

        AddressEntity updatedAddress = new AddressEntity(object);

        this.entityManager.merge(updatedAddress);
    }

    @Override
    public void delete(ArrayList<Integer> ids) throws BatchUpdateException {
        int result = 0;
        String query = "UPDATE AddressEntity SET isActive = false, lastUpdate = :date WHERE addressId = :addressId";

        for (int id : ids) {
            Query update = entityManager.createQuery(query)
                    .setParameter("addressId", id)
                    .setParameter("date", LocalDateTime.now());
            result += update.executeUpdate();
        }

        if (result != ids.size()) {
            int deleteFails = ids.size() - result;

            throw new BatchUpdateException("Erro ao deletar " + deleteFails + " endereços. Nenhum endereço deletado.");
        }
    }
}
