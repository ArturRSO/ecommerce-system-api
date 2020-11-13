package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.OrderModel;
import ecommerce.system.api.repositories.IOrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class OrderRepository implements IOrderRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public int createOrder(OrderModel order) {
        // TODO
        return 0;
    }

    @Override
    public int createOrderSummary(OrderModel order) {
        // TODO
        return 0;
    }

    @Override
    public List<OrderModel> getOrdersByStoreId(int storeId) {
        // TODO
        return null;
    }

    @Override
    public List<OrderModel> getOrderSummariesByUserId(int userId) {
        // TODO
        return null;
    }
}
