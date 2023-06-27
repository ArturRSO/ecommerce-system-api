package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.Telephone;
import ecommerce.system.api.models.UserTelephone;
import ecommerce.system.api.models.embedded.UserTelephoneKey;
import ecommerce.system.api.repositories.ITelephoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class TelephoneRepository implements ITelephoneRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(Telephone object) {

        this.entityManager.persist(object);
        this.entityManager.flush();

        return object.getTelephoneId();
    }

    @Override
    public void relateTelephoneAndUser(int userId, int telephoneId) {

        UserTelephoneKey userTelephoneKey = new UserTelephoneKey(userId, telephoneId);
        UserTelephone userTelephone = new UserTelephone(userTelephoneKey);

        this.entityManager.persist(userTelephone);
    }

    @Override
    public Telephone getById(int id) {

        try {

            String query = "FROM Telephone t WHERE t.active = true AND t.telephoneId = :telephoneId";
            TypedQuery<Telephone> result = this.entityManager.createQuery(query, Telephone.class)
                    .setParameter("telephoneId", id);
            Telephone telephone = result.getSingleResult();

            return telephone;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<Telephone> getTelephonesByUserId(int userId) {

        String query = "SELECT t FROM Telephone t, UserTelephone ut WHERE t.active = true AND ut.id.userId = :userId AND t.telephoneId = ut.id.telephoneId ORDER by t.telephoneId ASC";
        TypedQuery<Telephone> result = this.entityManager.createQuery(query, Telephone.class)
                .setParameter("userId", userId);
        List<Telephone> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public boolean update(Telephone object) {

        Telephone telephone = this.entityManager.find(Telephone.class, object.getTelephoneId());

        if (telephone == null || !telephone.isActive()) {
            return false;
        }

        object.setCreationDate(telephone.getCreationDate());
        object.setActive(telephone.isActive());

        this.entityManager.merge(object);

        return true;
    }

    @Override
    public boolean delete(int id) {

        Telephone telephone = this.entityManager.find(Telephone.class, id);

        if (telephone == null || !telephone.isActive()) {
            return false;
        }

        telephone.setActive(false);
        telephone.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(telephone);

        return true;
    }
}
