package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.StoreEntity;
import ecommerce.system.api.entities.StoreUserEntity;
import ecommerce.system.api.entities.embedded.StoreUserKey;
import ecommerce.system.api.models.StoreModel;
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
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class StoreRepository implements IStoreRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(StoreModel object) {

        StoreEntity store = new StoreEntity(object);

        this.entityManager.persist(store);
        this.entityManager.flush();

        return store.getStoreId();
    }

    @Override
    public void relateStoreAndUser(int storeId, int userId) {

        StoreUserKey storeUserKey = new StoreUserKey(storeId, userId);
        StoreUserEntity storeUser = new StoreUserEntity(storeUserKey);

        this.entityManager.persist(storeUser);
    }

    @Override
    public List<StoreModel> getAllStores() {

        String query = "FROM StoreEntity s WHERE s.active = true ORDER BY s.storeId ASC";
        TypedQuery<StoreEntity> result = this.entityManager.createQuery(query, StoreEntity.class);
        List<StoreEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreModel> stores = new ArrayList<>();
        (entities).forEach((store) -> stores.add(store.toModel()));

        return stores;
    }

    @Override
    public List<StoreModel> getStoresByUserId(int userId) {

        String query = "SELECT s FROM StoreEntity s, StoreUserEntity su WHERE s.storeId = su.id.storeId AND su.id.userId = :userId AND s.active = true ORDER BY s.storeId ASC";
        TypedQuery<StoreEntity> result = this.entityManager.createQuery(query, StoreEntity.class)
                .setParameter("userId", userId);
        List<StoreEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreModel> stores = new ArrayList<>();
        (entities).forEach((store) -> stores.add(store.toModel()));

        return stores;
    }

    @Override
    public List<Integer> getUserIdsByStoreId(int storeId) {

        String query = "SELECT su.id.userId FROM StoreUserEntity su, StoreEntity s WHERE s.storeId = su.id.storeId AND s.storeId = :storeId AND s.active = true ORDER BY s.storeId ASC";
        TypedQuery<Integer> result = this.entityManager.createQuery(query, Integer.class)
                .setParameter("storeId", storeId);
        List<Integer> userIds = result.getResultList();

        if (userIds == null || userIds.isEmpty()) {
            return null;
        }

        return userIds;
    }

    @Override
    public StoreModel getById(int id) {

        try {

            String query = "FROM StoreEntity s WHERE s.active = true AND s.storeId = :storeId";
            TypedQuery<StoreEntity> result = this.entityManager.createQuery(query, StoreEntity.class)
                    .setParameter("storeId", id);
            StoreEntity store = result.getSingleResult();

            return store.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public StoreModel getStoreByProductId(int productId) {

        try {

            String query = "SELECT s FROM StoreEntity s, ProductEntity p WHERE p.storeId = s.storeId AND p.productId = :productId";
            TypedQuery<StoreEntity> result = this.entityManager.createQuery(query, StoreEntity.class)
                    .setParameter("productId", productId);
            StoreEntity store = result.getSingleResult();

            return store.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean update(StoreModel object) {

        StoreEntity store = this.entityManager.find(StoreEntity.class, object.getStoreId());

        if (store == null || store.isActive()) {
            return false;
        }

        StoreEntity updatedStore = new StoreEntity(object);

        this.entityManager.merge(updatedStore);

        return true;
    }

    @Override
    public boolean delete(int id) {

        StoreEntity store = this.entityManager.find(StoreEntity.class, id);

        if (store == null || store.isActive()) {
            return false;
        }

        store.setActive(false);
        store.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(store);

        return true;
    }

}
