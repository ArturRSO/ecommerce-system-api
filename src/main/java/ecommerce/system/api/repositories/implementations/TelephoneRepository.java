package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.TelephoneEntity;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.repositories.ITelephoneRepository;
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
public class TelephoneRepository implements ITelephoneRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int create(TelephoneModel object) {

        TelephoneEntity telephone = new TelephoneEntity(object);

        this.entityManager.persist(telephone);
        this.entityManager.flush();

        return telephone.getTelephoneId();
    }

    @Override
    public TelephoneModel getById(int id) {

        try {

            String query = "FROM TelephoneEntity t WHERE t.active = true AND t.telephoneId = :telephoneId";
            TypedQuery<TelephoneEntity> result = this.entityManager.createQuery(query, TelephoneEntity.class)
                    .setParameter("telephoneId", id);
            TelephoneEntity telephone = result.getSingleResult();

            return telephone.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<TelephoneModel> getTelephonesByUserId(int userId) {

        String query = "FROM TelephoneEntity t WHERE t.active = true AND t.userId = :userId ORDER by t.telephoneId ASC";
        TypedQuery<TelephoneEntity> result = this.entityManager.createQuery(query, TelephoneEntity.class)
                .setParameter("userId", userId);
        List<TelephoneEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<TelephoneModel> telephones = new ArrayList<>();
        (entities).forEach((telephone) -> telephones.add(telephone.toModel()));

        return telephones;
    }

    @Override
    public boolean update(TelephoneModel object) {

        TelephoneEntity telephone = this.entityManager.find(TelephoneEntity.class, object.getTelephoneId());

        if (telephone == null || !telephone.isActive()) {
            return false;
        }

        TelephoneEntity updatedTelephone = new TelephoneEntity(object);
        this.entityManager.merge(updatedTelephone);

        return true;
    }

    @Override
    public boolean delete(int id) {

        TelephoneEntity telephone = this.entityManager.find(TelephoneEntity.class, id);

        if (telephone == null || !telephone.isActive()) {
            return false;
        }

        telephone.setActive(false);
        telephone.setLastUpdate(LocalDateTime.now());

        this.entityManager.merge(telephone);

        return true;
    }
}
