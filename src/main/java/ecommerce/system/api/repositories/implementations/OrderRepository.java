package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.dto.OrderItemDTO;
import ecommerce.system.api.models.Order;
import ecommerce.system.api.models.OrderSummary;
import ecommerce.system.api.models.ProductOrder;
import ecommerce.system.api.models.embedded.ProductOrderKey;
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
@Transactional(rollbackOn = { Exception.class })
public class OrderRepository implements IOrderRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int createOrder(Order order, int storeId) {

        order.setStoreId(storeId);

        this.entityManager.persist(order);
        this.entityManager.flush();

        return order.getOrderId();
    }

    @Override
    public int createOrderSummary(Order order) {

        OrderSummary orderSummary = new OrderSummary(order);

        this.entityManager.persist(orderSummary);
        this.entityManager.flush();

        return orderSummary.getOrderSummaryId();
    }

    @Override
    public void createProductOrder(int productId, int orderId, int quantity) {

        ProductOrderKey productOrderKey = new ProductOrderKey(productId, orderId);
        ProductOrder productOrder = new ProductOrder(productOrderKey, quantity);

        this.entityManager.persist(productOrder);
    }

    @Override
    public List<Order> getOrdersByStoreId(int storeId) {

        String query = "FROM Order o WHERE o.storeId = :storeId";
        TypedQuery<Order> result = this.entityManager.createQuery(query, Order.class)
                .setParameter("storeId", storeId);
        List<Order> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        (entities).forEach((entity) -> {
            List<OrderItemDTO> itens = this.getItensByOrderId(entity.getOrderId());
            entity.setItens(itens);
        });

        return entities;
    }

    @Override
    public List<Order> getOrdersByProductId(int productId) {

        String query = "SELECT o FROM Order o, ProductOrder po WHERE o.orderId = po.id.orderId AND po.id.productId = :productId";
        TypedQuery<Order> result = this.entityManager.createQuery(query, Order.class)
                .setParameter("productId", productId);
        List<Order> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<Order> getOrderSummariesByUserId(int userId) {

        String query = "FROM OrderSummary os WHERE os.userId = :userId";
        TypedQuery<OrderSummary> result = this.entityManager.createQuery(query, OrderSummary.class)
                .setParameter("userId", userId);
        List<OrderSummary> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<Order> orders = new ArrayList<>();
        (entities).forEach((entity) -> {
            Order order = new Order(entity);
            order.setItens(this.getItensByOrderSummaryId(entity.getOrderSummaryId()));
            orders.add(order);
        });

        return orders;
    }

    @Override
    public List<Order> getOrdersByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT o FROM Order o, OrderSummary os WHERE o.orderSummaryId = os.orderSummaryId AND os.orderSummaryId = :orderSummaryId ORDER BY o.orderId ASC";
        TypedQuery<Order> result = this.entityManager.createQuery(query, Order.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<Order> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public Order getOrderById(int orderId) {

        try {

            String query = "FROM Order o WHERE o.orderId = :orderId";
            TypedQuery<Order> result = this.entityManager.createQuery(query, Order.class)
                    .setParameter("orderId", orderId);

            Order entity = result.getSingleResult();

            entity.setItens(this.getItensByOrderId(entity.getOrderId()));

            return entity;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public Order getOrderSummaryById(int orderSummaryId) {

        try {

            String query = "FROM OrderSummary os WHERE os.orderSummaryId = :orderSummaryId";
            TypedQuery<OrderSummary> result = this.entityManager.createQuery(query, OrderSummary.class)
                    .setParameter("orderSummaryId", orderSummaryId);

            OrderSummary entity = result.getSingleResult();

            Order order = new Order(entity);
            order.setItens(this.getItensByOrderSummaryId(entity.getOrderSummaryId()));

            return order;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean updateOrder(Order order) {

        Order entity = this.entityManager.find(Order.class, order.getOrderId());

        if (entity == null) {
            return false;
        }

        Order updatedOrder = new Order(order, true);
        this.entityManager.merge(updatedOrder);

        return true;
    }

    @Override
    public boolean updateOrderSummary(Order orderSummary) {

        OrderSummary entity = this.entityManager.find(OrderSummary.class, orderSummary.getOrderSummaryId());

        if (entity == null) {
            return false;
        }

        OrderSummary updatedOrder = new OrderSummary(orderSummary);
        this.entityManager.merge(updatedOrder);

        return true;
    }

    private List<OrderItemDTO> getItensByOrderId(int orderId) {

        String query = "FROM ProductOrder po WHERE po.id.orderId = :orderId";
        TypedQuery<ProductOrder> result = this.entityManager.createQuery(query, ProductOrder.class)
                .setParameter("orderId", orderId);
        List<ProductOrder> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<OrderItemDTO> itens = new ArrayList<>();
        (entities).forEach((item) -> itens.add(item.toDTO()));

        return itens;
    }

    private List<OrderItemDTO> getItensByOrderSummaryId(int orderSummaryId) {

        String query = "SELECT po FROM ProductOrder po, Order o WHERE o.orderId = po.id.orderId AND o.orderSummaryId = :orderSummaryId";
        TypedQuery<ProductOrder> result = this.entityManager.createQuery(query, ProductOrder.class)
                .setParameter("orderSummaryId", orderSummaryId);
        List<ProductOrder> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<OrderItemDTO> itens = new ArrayList<>();
        (entities).forEach((item) -> itens.add(item.toDTO()));

        return itens;
    }
}
