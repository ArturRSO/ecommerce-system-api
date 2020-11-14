package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.DeliveryEntity;
import ecommerce.system.api.models.DeliveryModel;
import ecommerce.system.api.repositories.IDeliveryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class DeliveryRepository implements IDeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int createDelivery(DeliveryModel delivery) {

        DeliveryEntity deliveryEntity = new DeliveryEntity(delivery);

        this.entityManager.persist(deliveryEntity);
        this.entityManager.flush();

        return deliveryEntity.getDeliveryId();
    }

    @Override
    public List<DeliveryModel> getDeliveriesByOrderId(int orderId) {

        String query = "FROM DeliveryEntity d WHERE d.orderId = :orderId";
        TypedQuery<DeliveryEntity> result = this.entityManager.createQuery(query, DeliveryEntity.class);
        List<DeliveryEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<DeliveryModel> deliveries = new ArrayList<>();
        (entities).forEach((delivery) -> deliveries.add(delivery.toModel()));

        return deliveries;
    }

    @Override
    public boolean updateDeliveryStatus(int deliveryId, boolean status) {

        DeliveryEntity delivery = this.entityManager.find(DeliveryEntity.class, deliveryId);

        if (delivery == null) {
            return false;
        }

        delivery.setSuccess(status);
        return true;
    }
}
