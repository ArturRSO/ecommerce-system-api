package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.UserEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.CredentialsModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserRepository implements IUserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(UserModel object) {

        UserEntity user = new UserEntity(object);

        this.entityManager.persist(user);
    }

    @Override
    public List<UserModel> getAll() throws EmptySearchException {

        String query = "FROM UserEntity u WHERE u.isActive = true ORDER BY u.userId ASC";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class);
        List<UserEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhum usuário encontrado!");
        }

        ArrayList<UserModel> users = new ArrayList<>();
        (entities).forEach((user) -> users.add(user.toModel()));

        return users;
    }

    @Override
    public UserModel getById(int id) {

        String query = "FROM UserEntity u WHERE u.isActive = true AND u.userId = :userId";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                .setParameter("userId", id);
        UserEntity user = result.getSingleResult();

        return user.toModel();
    }

    @Override
    public UserModel getUserByEmail(String email) {

        String query = "FROM UserEntity u WHERE u.isActive = true AND u.email = :email";
        TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                .setParameter("email", email);

        UserEntity user = result.getSingleResult();

        return user.toModel();
    }

    @Override
    public boolean checkUserByEmail(String email, boolean isActive) {

        logger.info("Checking user by e-mail...");

        try {
            String query = "FROM UserEntity u WHERE u.isActive = true AND u.email = :email";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("email", email);

            UserEntity user = result.getSingleResult();

            logger.info("E-mail successfully checked for user with Id: " + user.getUserId());

            return true;

        } catch (Exception e) {

            logger.warn("Check failed for e-mail: " + email);
            logger.error(e.getMessage());

            return false;
        }
    }

    @Override
    public boolean checkUserCredentials(CredentialsModel credentials) {

        try {
            String query = "FROM UserEntity u WHERE u.email = :email AND u.password = :password AND u.isActive = true";
            TypedQuery<UserEntity> result = this.entityManager.createQuery(query, UserEntity.class)
                    .setParameter("email", credentials.getEmail())
                    .setParameter("password", credentials.getPassword());

            UserEntity user = result.getSingleResult();

            logger.info("Credentials succesfully checked for user with id " + user.getUserId());

            return true;

        } catch (Exception e) {

            logger.warn("Failed credentials check for e-mail " + credentials.getEmail());
            logger.error(e.getMessage());

            return false;
        }
    }

    @Override
    public void update(UserModel object) {

        UserEntity updatedUser = new UserEntity(object);

        this.entityManager.merge(updatedUser);
    }

    @Override
    public void delete(List<Integer> ids) throws BatchUpdateException {
        int result = 0;
        String query = "UPDATE UserEntity SET isActive = false, lastUpdate = :date WHERE userId = :userId";

        for (int id : ids) {
            Query update = entityManager.createQuery(query)
                    .setParameter("userId", id)
                    .setParameter("date", LocalDateTime.now());
            result += update.executeUpdate();
        }

        if (result != ids.size()) {
            int deleteFails = ids.size() - result;

            throw new BatchUpdateException("Erro ao deletar " + deleteFails + " usuários. Nenhum usuário deletado!");
        }
    }
}
