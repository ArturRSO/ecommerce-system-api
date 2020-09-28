package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.TelephoneEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.repositories.ITelephoneRepository;
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
public class TelephoneRepository implements ITelephoneRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(TelephoneModel object) {

        TelephoneEntity telephone = new TelephoneEntity(object);

        this.entityManager.persist(telephone);
    }

    @Override
    public List<TelephoneModel> getAll() throws EmptySearchException {

        String query = "FROM TelephoneEntity t WHERE t.isActive = true ORDER by t.telephoneId ASC";
        TypedQuery<TelephoneEntity> result = this.entityManager.createQuery(query, TelephoneEntity.class);
        List<TelephoneEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhum telefone encontrado!");
        }

        ArrayList<TelephoneModel> telephones = new ArrayList<>();
        (entities).forEach((telephone) -> telephones.add(telephone.toModel()));

        return telephones;
    }

    @Override
    public TelephoneModel getById(int id) {

        String query = "FROM TelephoneEntity t WHERE t.isActive = true AND t.telephone = :telephoneId";
        TypedQuery<TelephoneEntity> result = this.entityManager.createQuery(query, TelephoneEntity.class)
                .setParameter("telephoneId", id);
        TelephoneEntity telephone = result.getSingleResult();

        return telephone.toModel();
    }

    @Override
    public List<TelephoneModel> getTelephonesByUserId(int userId) throws EmptySearchException {

        String query = "FROM TelephoneEntity t WHERE t.isActive = true AND t.userId = :userId ORDER by t.telephoneId ASC";
        TypedQuery<TelephoneEntity> result = this.entityManager.createQuery(query, TelephoneEntity.class)
                .setParameter("userId", userId);
        List<TelephoneEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhum telefone encontrado!");
        }

        ArrayList<TelephoneModel> telephones = new ArrayList<>();
        (entities).forEach((telephone) -> telephones.add(telephone.toModel()));

        return telephones;
    }

    @Override
    public void update(TelephoneModel object) {

        TelephoneEntity updatedTelephone = new TelephoneEntity(object);

        this.entityManager.merge(updatedTelephone);
    }

    @Override
    public void delete(List<Integer> ids) throws BatchUpdateException {
        int result = 0;
        String query = "UPDATE TelephoneEntity SET isActive = false, lastUpdate = :date WHERE telephoneId = :telephoneId";

        for (int id : ids) {
            Query update = entityManager.createQuery(query)
                    .setParameter("telephoneId", id)
                    .setParameter("date", LocalDateTime.now());
            result += update.executeUpdate();
        }

        if (result != ids.size()) {
            int deleteFails = ids.size() - result;

            throw new BatchUpdateException("Erro ao deletar " + deleteFails + " telefones. Nenhum telefone deletado!");
        }

    }
}
