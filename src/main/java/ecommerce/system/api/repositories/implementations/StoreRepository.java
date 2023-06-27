package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.Store;
import ecommerce.system.api.models.StoreUser;
import ecommerce.system.api.models.embedded.StoreUserKey;
import ecommerce.system.api.repositories.IStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class StoreRepository implements IStoreRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Store object) {

        this.entityManager.persist(object);
        this.entityManager.flush();

        return object.getStoreId();
    }

    @Override
    public void relateStoreAndUser(int storeId, int userId) {

        StoreUserKey storeUserKey = new StoreUserKey(storeId, userId);
        StoreUser storeUser = new StoreUser(storeUserKey);

        this.entityManager.persist(storeUser);
    }

    @Override
    public List<Store> getAllStores() {

        String query = "FROM Store s WHERE s.active = true ORDER BY s.storeId ASC";
        TypedQuery<Store> result = this.entityManager.createQuery(query, Store.class);
        List<Store> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<Store> getStoresByUserId(int userId) {

        String query = "SELECT s FROM Store s, StoreUser su WHERE s.storeId = su.id.storeId AND su.id.userId = :userId AND s.active = true ORDER BY s.storeId ASC";
        TypedQuery<Store> result = this.entityManager.createQuery(query, Store.class)
                .setParameter("userId", userId);
        List<Store> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<Integer> getUserIdsByStoreId(int storeId) {

        String query = "SELECT su.id.userId FROM StoreUser su, Store s WHERE s.storeId = su.id.storeId AND s.storeId = :storeId AND s.active = true ORDER BY s.storeId ASC";
        TypedQuery<Integer> result = this.entityManager.createQuery(query, Integer.class)
                .setParameter("storeId", storeId);
        List<Integer> userIds = result.getResultList();

        if (userIds == null || userIds.isEmpty()) {
            return null;
        }

        return userIds;
    }

    @Override
    public Store getById(int id) {

        try {

            String query = "FROM Store s WHERE s.active = true AND s.storeId = :storeId";
            TypedQuery<Store> result = this.entityManager.createQuery(query, Store.class)
                    .setParameter("storeId", id);
            Store store = result.getSingleResult();

            return store;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public Store getStoreByProductId(int productId) {

        try {

            String query = "SELECT s FROM Store s, ProductEntity p WHERE p.storeId = s.storeId AND p.productId = :productId";
            TypedQuery<Store> result = this.entityManager.createQuery(query, Store.class)
                    .setParameter("productId", productId);
            Store store = result.getSingleResult();

            return store;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean update(Store object) {

        Store store = this.entityManager.find(Store.class, object.getStoreId());

        if (store == null || !store.isActive()) {
            return false;
        }

        this.entityManager.merge(object);

        return true;
    }

    @Override
    public boolean delete(int id) {

        Store store = this.entityManager.find(Store.class, id);

        if (store == null || !store.isActive()) {
            return false;
        }

        store.setActive(false);
        store.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(store);

        return true;
    }

}
