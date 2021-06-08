package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.entities.OrderEntity;
import ecommerce.system.api.entities.OrderSummaryEntity;
import ecommerce.system.api.entities.ProductOrderEntity;
import ecommerce.system.api.entities.embedded.ProductOrderKey;
import ecommerce.system.api.models.OrderModel;
import ecommerce.system.api.repositories.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class OrderRepository implements IOrderRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            List<OrderItemDTO>  itens = this.getItensByOrderId(entity.getOrderId());
            order.setItens(itens);
            orders.add(order);
        });

        return orders;
    }

    @Override
    public List<OrderModel> getOrdersByProductId(int productId) {

        String query = "SELECT o FROM OrderEntity o, ProductOrderEntity po WHERE o.orderId = po.id.orderId AND po.id.productId = :productId";
        TypedQuery<OrderEntity> result = this.entityManager.createQuery(query, OrderEntity.class)
                .setParameter("productId", productId);
        List<OrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<OrderModel> orders = new ArrayList<>();

        (entities).forEach((entity) -> orders.add(entity.toModel()));

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
            order.setItens(this.getItensByOrderSummaryId(entity.getOrderSummaryId()));
            orders.add(order);
        });

        return orders;
    }

    @Override
    public List<OrderModel> getOrdersByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT o FROM OrderEntity o, OrderSummaryEntity os WHERE o.orderSummaryId = os.orderSummaryId AND os.orderSummaryId = :orderSummaryId ORDER BY o.orderId ASC";
        TypedQuery<OrderEntity> result = this.entityManager.createQuery(query, OrderEntity.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<OrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<OrderModel> orders = new ArrayList<>();
        (entities).forEach((entity) -> orders.add(entity.toModel()));

        return orders;
    }

    @Override
    public OrderModel getOrderSummaryById(int orderSummaryId) {

        try {

            String query = "FROM OrderSummaryEntity os WHERE os.orderSummaryId = :orderSummaryId";
            TypedQuery<OrderSummaryEntity> result = this.entityManager.createQuery(query, OrderSummaryEntity.class)
                    .setParameter("orderSummaryId", orderSummaryId);

            OrderSummaryEntity entity = result.getSingleResult();

            OrderModel order = entity.toModel();
            order.setItens(this.getItensByOrderSummaryId(entity.getOrderSummaryId()));

            return order;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean updateOrder(OrderModel order) {

        OrderEntity entity = this.entityManager.find(OrderEntity.class, order.getOrderId());

        if (entity == null) {
            return false;
        }

        OrderEntity updatedOrder = new OrderEntity(order);
        this.entityManager.merge(updatedOrder);

        return true;
    }

    @Override
    public boolean updateOrderSummary(OrderModel orderSummary) {

        OrderSummaryEntity entity = this.entityManager.find(OrderSummaryEntity.class, orderSummary.getOrderId());

        if (entity == null) {
            return false;
        }

        OrderEntity updatedOrder = new OrderEntity(orderSummary);
        this.entityManager.merge(updatedOrder);

        return true;
    }

    private List<OrderItemDTO> getItensByOrderId(int orderId) {

        String query = "FROM ProductOrderEntity po WHERE po.id.orderId = :orderId";
        TypedQuery<ProductOrderEntity> result = this.entityManager.createQuery(query, ProductOrderEntity.class)
                .setParameter("orderId", orderId);
        List<ProductOrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<OrderItemDTO> itens = new ArrayList<>();
        (entities).forEach((item) -> itens.add(item.toDTO()));

        return itens;
    }

    private List<OrderItemDTO> getItensByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT po FROM ProductOrderEntity po, OrderEntity o WHERE o.orderId = po.id.orderId AND o.orderSummaryId = :orderSummaryId";
        TypedQuery<ProductOrderEntity> result = this.entityManager.createQuery(query, ProductOrderEntity.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<ProductOrderEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<OrderItemDTO> itens = new ArrayList<>();
        (entities).forEach((item) -> itens.add(item.toDTO()));

        return itens;
    }
}
