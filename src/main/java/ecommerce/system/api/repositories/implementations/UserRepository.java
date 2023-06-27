package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.dto.CredentialsDTO;
import ecommerce.system.api.models.User;
import ecommerce.system.api.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class UserRepository implements IUserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(User object) {

        this.entityManager.persist(object);
        this.entityManager.flush();

        return object.getUserId();
    }

    @Override
    public List<User> getAllUsers() {

        String query = "FROM User u WHERE u.active = true ORDER BY u.userId ASC";
        TypedQuery<User> result = this.entityManager.createQuery(query, User.class);
        List<User> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public User getById(int id) {

        try {

            String query = "FROM User u WHERE u.active = true AND u.userId = :userId";
            TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                    .setParameter("userId", id);
            User user = result.getSingleResult();

            return user;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<User> getUsersByRoleId(int roleId) {

        String query = "FROM User u WHERE u.active = true AND u.roleId = :roleId";
        TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                .setParameter("roleId", roleId);

        List<User> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<User> getUsersByStoreId(int storeId) {

        String query = "SELECT u FROM User u, StoreUser su WHERE u.userId = su.id.userId AND su.id.storeId = :storeId AND u.active = true";
        TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                .setParameter("storeId", storeId);

        List<User> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public User getUserByDocumentNumber(String documentNumber) {

        try {

            String query = "FROM User u WHERE u.documentNumber = :documentNumber";
            TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                    .setParameter("documentNumber", documentNumber);

            User user = result.getSingleResult();

            return user;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {

        try {

            String query = "FROM User u WHERE u.email = :email AND u.active = true";
            TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                    .setParameter("email", email);

            User user = result.getSingleResult();

            return user;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public User getUserByCredentials(CredentialsDTO credentials) {

        try {

            String query = "FROM User u WHERE u.email = :email AND u.password = :password AND u.active = true";
            TypedQuery<User> result = this.entityManager.createQuery(query, User.class)
                    .setParameter("email", credentials.getEmail())
                    .setParameter("password", credentials.getPassword());

            User user = result.getSingleResult();

            logger.info("Credentials succesfully checked for user with id " + user.getUserId());

            return user;

        } catch (NoResultException nre) {

            logger.warn("Failed credentials check for e-mail " + credentials.getEmail());
            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean update(User object) {

        User user = this.entityManager.find(User.class, object.getUserId());

        if (user == null || !user.isActive()) {
            return false;
        }

        this.entityManager.merge(object);

        return true;
    }

    @Override
    public boolean delete(int id) {

        User user = this.entityManager.find(User.class, id);

        if (user == null || !user.isActive()) {
            return false;
        }

        user.setActive(false);
        user.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(user);

        return true;
    }
}
