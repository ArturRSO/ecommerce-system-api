package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.UserEntity;
import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
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
public class UserRepository implements IUserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(UserModel object) {

        UserEntity user = new UserEntity(object);

        this.entityManager.persist(user);
        this.entityManager.flush();

        return user.getUserId();
    }

    @Override
    public List<UserModel> getAll() {

        String query = "FROM UserEntity u WHERE u.active = true ORDER BY u.userId ASC";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class);
        List<UserEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<UserModel> users = new ArrayList<>();
        (entities).forEach((user) -> users.add(user.toModel()));

        return users;
    }

    @Override
    public UserModel getById(int id) {

        try {

            String query = "FROM UserEntity u WHERE u.active = true AND u.userId = :userId";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("userId", id);
            UserEntity user = result.getSingleResult();

            return user.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<UserModel> getUsersByRoleId(int roleId) {

        String query = "FROM UserEntity u WHERE u.active = true AND u.roleId = :roleId";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                .setParameter("roleId", roleId);

        List<UserEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<UserModel> users = new ArrayList<>();
        (entities).forEach((user) -> users.add(user.toModel()));

        return users;
    }

    @Override
    public List<UserModel> getUsersByStoreId(int storeId) {

        String query = "SELECT u FROM UserEntity u, StoreUserEntity su WHERE u.userId = su.id.userId AND su.id.storeId = :storeId AND u.active = true";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                .setParameter("storeId", storeId);

        List<UserEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<UserModel> users = new ArrayList<>();
        (entities).forEach((user) -> users.add(user.toModel()));

        return users;
    }

    @Override
    public UserModel getUserByDocumentNumber(String documentNumber) {

        try {

            String query = "FROM UserEntity u WHERE u.documentNumber = :documentNumber";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("documentNumber", documentNumber);

            UserEntity user = result.getSingleResult();

            return user.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public UserModel getUserByEmail(String email) {

        try {

            String query = "FROM UserEntity u WHERE u.email = :email";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("email", email);

            UserEntity user = result.getSingleResult();

            return user.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public UserModel getUserByCredentials(CredentialsModel credentials) {

        try {

            String query = "FROM UserEntity u WHERE u.email = :email AND u.password = :password AND u.active = true";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("email", credentials.getEmail())
                    .setParameter("password", credentials.getPassword());

            UserEntity user = result.getSingleResult();

            logger.info("Credentials succesfully checked for user with id " + user.getUserId());

            return user.toModel();

        } catch (NoResultException nre) {

            logger.warn("Failed credentials check for e-mail " + credentials.getEmail());
            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean update(UserModel object) {

        UserEntity user = this.entityManager.find(UserEntity.class, object.getUserId());

        if (user == null || !user.isActive()) {
            return false;
        }

        UserEntity updatedUser = new UserEntity(object);
        this.entityManager.merge(updatedUser);

        return true;
    }

    @Override
    public boolean delete(int id)  {

        UserEntity user = this.entityManager.find(UserEntity.class, id);

        if (user == null || !user.isActive()) {
            return false;
        }

        user.setActive(false);
        user.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(user);

        return true;
    }
}
