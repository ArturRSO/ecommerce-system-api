package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.UserOptionEntity;
import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.UserOptionModel;
import ecommerce.system.api.repositories.IUserOptionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class UserOptionRepository implements IUserOptionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserOptionModel> getUserOptionsByRoleId(int roleId) throws EmptySearchException {

        String query = "SELECT uo FROM UserOptionEntity uo, RoleUserOptionEntity ru WHERE uo.userOptionId = ru.id.userOptionId AND ru.id.roleId = :roleId";
        TypedQuery<UserOptionEntity> result = this.entityManager.createQuery(query, UserOptionEntity.class)
                .setParameter("roleId", roleId);
        List<UserOptionEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            throw new EmptySearchException("Nenhuma opção encontrada!");
        }

        ArrayList<UserOptionModel> options = new ArrayList<>();
        (entities).forEach((option) -> options.add(option.toModel()));

        return options;
    }
}
