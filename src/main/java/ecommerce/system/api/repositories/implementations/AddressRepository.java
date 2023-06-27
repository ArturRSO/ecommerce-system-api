package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.Address;
import ecommerce.system.api.models.UserAddress;
import ecommerce.system.api.models.embedded.UserAddressKey;
import ecommerce.system.api.repositories.IAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class AddressRepository implements IAddressRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Address object) {

        this.entityManager.persist(object);
        this.entityManager.flush();

        return object.getAddressId();
    }

    @Override
    public void relateAddressAndUser(int userId, int adddressId) {

        UserAddressKey userAddressKey = new UserAddressKey(userId, adddressId);
        UserAddress userAddress = new UserAddress(userAddressKey);

        this.entityManager.persist(userAddress);
    }

    @Override
    public Address getById(int id) {

        try {

            String query = "FROM Address a WHERE a.active = true AND a.addressId = :addressId";
            TypedQuery<Address> result = this.entityManager.createQuery(query, Address.class)
                    .setParameter("addressId", id);
            Address address = result.getSingleResult();

            return address;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<Address> getAddressesByUserId(int userId) {

        String query = "SELECT a FROM Address a, UserAddress ua WHERE a.active = true AND ua.id.userId = :userId AND a.addressId = ua.id.addressId ORDER BY a.addressId ASC";
        TypedQuery<Address> result = this.entityManager.createQuery(query, Address.class)
                .setParameter("userId", userId);
        List<Address> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public boolean update(Address object) {

        Address address = this.entityManager.find(Address.class, object.getAddressId());

        if (address == null || !address.isActive()) {
            return false;
        }

        object.setCreationDate(address.getCreationDate());
        object.setActive(address.isActive());

        this.entityManager.merge(object);

        return true;
    }

    @Override
    public boolean delete(int id) {

        Address address = this.entityManager.find(Address.class, id);

        if (address == null || !address.isActive()) {
            return false;
        }

        address.setActive(false);
        address.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(address);

        return true;
    }
}
