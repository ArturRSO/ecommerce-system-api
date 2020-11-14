package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.OrderEntity;
import ecommerce.system.api.entities.OrderSummaryEntity;
import ecommerce.system.api.entities.ProductOrderEntity;
import ecommerce.system.api.entities.embedded.ProductOrderKey;
import ecommerce.system.api.models.OrderModel;
import ecommerce.system.api.repositories.IOrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class OrderRepository implements IOrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int createOrder(OrderModel order, int storeId) {

        OrderEntity orderEntity = new OrderEntity(order);
        orderEntity.setStoreId(storeId);

        this.entityManager.persist(orderEntity);
        this.entityManager.flush();

        return orderEntity.getOrderId();
    }

    @Override
    public int createOrderSummary(OrderModel order) {

        OrderSummaryEntity orderSummaryEntity = new OrderSummaryEntity(order);

        this.entityManager.persist(orderSummaryEntity);
        this.entityManager.flush();

        return orderSummaryEntity.getOrderSummaryId();
    }

    @Override
    public void createProductOrder(int productId, int orderId, int quantity) {

        ProductOrderKey productOrderKey = new ProductOrderKey(productId, orderId);
        ProductOrderEntity productOrder = new ProductOrderEntity(productOrderKey, quantity);

        this.entityManager.persist(productOrder);
    }

    @Override
    public List<OrderModel> getOrdersByStoreId(int storeId) {

        String query = "FROM OrderEntity o WHERE o.storeId = :storeId";
        TypedQuery<OrderEntity> result = this.entityManager.createQuery(query, OrderEntity.class)
                .setParameter("storeId", storeId);
        List<OrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<OrderModel> orders = new ArrayList<>();
        (entities).forEach((entity) -> {
            OrderModel order = entity.toModel();
            order.setProducts(this.getProductsByOrderId(entity.getOrderId()));
            orders.add(order);
        });

        return orders;
    }

    @Override
    public List<OrderModel> getOrderSummariesByUserId(int userId) {

        String query = "FROM OrderSummaryEntity os WHERE os.userId = :userId";
        TypedQuery<OrderSummaryEntity> result = this.entityManager.createQuery(query, OrderSummaryEntity.class)
                .setParameter("userId", userId);
        List<OrderSummaryEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<OrderModel> orders = new ArrayList<>();
        (entities).forEach((entity) -> {
            OrderModel order = entity.toModel();
            order.setProducts(this.getProductsByOrderSummaryId(entity.getOrderSummaryId()));
            orders.add(order);
        });

        return orders;
    }

    private List<Map<String, Integer>> getProductsByOrderId(int orderId) {

        String query = "FROM ProductOrderEntity po WHERE po.id.orderId = :orderId";
        TypedQuery<ProductOrderEntity> result = this.entityManager.createQuery(query, ProductOrderEntity.class)
                .setParameter("orderId", orderId);
        List<ProductOrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<Map<String, Integer>> products = new ArrayList<>();
        (entities).forEach((product) -> products.add(product.toMap()));

        return products;
    }

    private List<Map<String, Integer>> getProductsByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT po FROM ProductOrderEntity po, OrderEntity o WHERE o.orderId = po.id.orderId AND o.orderSummaryId = :orderSummaryId";
        TypedQuery<ProductOrderEntity> result = this.entityManager.createQuery(query, ProductOrderEntity.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<ProductOrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<Map<String, Integer>> products = new ArrayList<>();
        (entities).forEach((product) -> products.add(product.toMap()));

        return products;
    }
}
