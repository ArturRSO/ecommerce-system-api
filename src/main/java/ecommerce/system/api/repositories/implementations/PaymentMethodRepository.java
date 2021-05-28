package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.PaymentMethodEntity;
import ecommerce.system.api.models.PaymentMethodModel;
import ecommerce.system.api.repositories.IPaymentMethodRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class PaymentMethodRepository implements IPaymentMethodRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PaymentMethodModel> getPaymentMethods() {

        String query = "FROM PaymentMethodEntity pm WHERE pm.enable = true";
        TypedQuery<PaymentMethodEntity> result = this.entityManager.createQuery(query, PaymentMethodEntity.class);
        List<PaymentMethodEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<PaymentMethodModel> paymentMethods = new ArrayList<>();
        (entities).forEach((paymentMethod) -> paymentMethods.add(paymentMethod.toModel()));

        return paymentMethods;
    }
}
