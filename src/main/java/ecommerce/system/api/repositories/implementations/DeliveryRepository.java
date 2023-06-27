package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.Delivery;
import ecommerce.system.api.repositories.IDeliveryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class DeliveryRepository implements IDeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int createDelivery(Delivery delivery) {

        this.entityManager.persist(delivery);
        this.entityManager.flush();

        return delivery.getDeliveryId();
    }

    @Override
    public List<Delivery> getDeliveriesByOrderId(int orderId) {

        String query = "FROM Delivery d WHERE d.orderId = :orderId";
        TypedQuery<Delivery> result = this.entityManager.createQuery(query, Delivery.class)
                .setParameter("orderId", orderId);
        List<Delivery> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<Delivery> getDeliveriesByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT d FROM Delivery d, Order o, OrderSummary os WHERE d.orderId = o.orderId AND o.orderSummaryId = os.orderSummaryId AND os.orderSummaryId = :orderId";
        TypedQuery<Delivery> result = this.entityManager.createQuery(query, Delivery.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<Delivery> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public boolean updateDeliveryStatus(int deliveryId, boolean status) {

        Delivery delivery = this.entityManager.find(Delivery.class, deliveryId);

        if (delivery == null) {
            return false;
        }

        delivery.setSuccess(status);
        return true;
    }
}
