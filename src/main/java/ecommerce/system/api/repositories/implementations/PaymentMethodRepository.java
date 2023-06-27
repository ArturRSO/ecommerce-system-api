package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.PaymentMethod;
import ecommerce.system.api.repositories.IPaymentMethodRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class PaymentMethodRepository implements IPaymentMethodRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PaymentMethod> getPaymentMethods() {

        String query = "FROM PaymentMethod pm WHERE pm.enable = true";
        TypedQuery<PaymentMethod> result = this.entityManager.createQuery(query, PaymentMethod.class);
        List<PaymentMethod> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }
}
