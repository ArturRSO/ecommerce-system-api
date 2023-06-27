package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.*;
import ecommerce.system.api.repositories.IReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class ReportRepository implements IReportRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OrdersByStoreReport> getOrdersReport() {

        String query = "FROM OrdersByStoreReport o";
        TypedQuery<OrdersByStoreReport> result = this.entityManager.createQuery(query,
                OrdersByStoreReport.class);
        List<OrdersByStoreReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public OrdersByStoreReport getOrdersReportByStoreId(int storeId) {

        try {

            String query = "FROM OrdersByStoreReport o WHERE o.storeId = :storeId";
            TypedQuery<OrdersByStoreReport> result = this.entityManager
                    .createQuery(query, OrdersByStoreReport.class)
                    .setParameter("storeId", storeId);
            OrdersByStoreReport ordersByStoreReport = result.getSingleResult();

            return ordersByStoreReport;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<ProductsByStoreReport> getProductsReport() {

        String query = "FROM ProductsByStoreReport p";
        TypedQuery<ProductsByStoreReport> result = this.entityManager.createQuery(query,
                ProductsByStoreReport.class);
        List<ProductsByStoreReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public ProductsByStoreReport getProductsReportByStoreId(int storeId) {

        try {

            String query = "FROM ProductsByStoreReport p WHERE p.storeId = :storeId";
            TypedQuery<ProductsByStoreReport> result = this.entityManager
                    .createQuery(query, ProductsByStoreReport.class)
                    .setParameter("storeId", storeId);
            ProductsByStoreReport productsByStoreReport = result.getSingleResult();

            return productsByStoreReport;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReport() {

        String query = "FROM StoreCashFlowReport s";
        TypedQuery<StoreCashFlowByOrderReport> result = this.entityManager.createQuery(query,
                StoreCashFlowByOrderReport.class);
        List<StoreCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByDateRange(LocalDate startDate,
            LocalDate endDate) {

        String query = "FROM StoreCashFlowReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<StoreCashFlowByOrderReport> result = this.entityManager
                .createQuery(query, StoreCashFlowByOrderReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<StoreCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreId(int storeId) {

        String query = "FROM StoreCashFlowReport s WHERE s.storeId = :storeId";
        TypedQuery<StoreCashFlowByOrderReport> result = this.entityManager
                .createQuery(query, StoreCashFlowByOrderReport.class)
                .setParameter("storeId", storeId);
        List<StoreCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreIdAndDateRange(int storeId,
            LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate AND s.storeId = :storeId";
        TypedQuery<StoreCashFlowByOrderReport> result = this.entityManager
                .createQuery(query, StoreCashFlowByOrderReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("storeId", storeId);
        List<StoreCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReport() {

        String query = "FROM StoreCashFlowRevenueReport s";
        TypedQuery<StoreCashFlowRevenueReport> result = this.entityManager.createQuery(query,
                StoreCashFlowRevenueReport.class);
        List<StoreCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate,
            LocalDate endDate) {

        String query = "FROM StoreCashFlowRevenueReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<StoreCashFlowRevenueReport> result = this.entityManager
                .createQuery(query, StoreCashFlowRevenueReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<StoreCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreId(int storeId) {

        String query = "FROM StoreCashFlowRevenueReport s WHERE s.storeId = :storeId";
        TypedQuery<StoreCashFlowRevenueReport> result = this.entityManager
                .createQuery(query, StoreCashFlowRevenueReport.class)
                .setParameter("storeId", storeId);
        List<StoreCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId,
            LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowRevenueReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate AND s.storeId = :storeId";
        TypedQuery<StoreCashFlowRevenueReport> result = this.entityManager
                .createQuery(query, StoreCashFlowRevenueReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("storeId", storeId);
        List<StoreCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoresByUserReport> getStoresByUserReport() {

        String query = "FROM StoresByUserReport s";
        TypedQuery<StoresByUserReport> result = this.entityManager.createQuery(query,
                StoresByUserReport.class);
        List<StoresByUserReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public StoresByUserReport getStoresByUserReportByUserId(int userId) {

        try {

            String query = "FROM StoresByUserReport s WHERE s.userId = :userId";
            TypedQuery<StoresByUserReport> result = this.entityManager
                    .createQuery(query, StoresByUserReport.class)
                    .setParameter("userId", userId);
            StoresByUserReport storesByUserReport = result.getSingleResult();

            return storesByUserReport;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReport() {

        String query = "FROM SystemCashFlowByOrderReport s";
        TypedQuery<SystemCashFlowByOrderReport> result = this.entityManager.createQuery(query,
                SystemCashFlowByOrderReport.class);
        List<SystemCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate,
            LocalDate endDate) {

        String query = "FROM SystemCashFlowByOrderReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<SystemCashFlowByOrderReport> result = this.entityManager
                .createQuery(query, SystemCashFlowByOrderReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<SystemCashFlowByOrderReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReport() {

        String query = "FROM SystemCashFlowRevenueReport s";
        TypedQuery<SystemCashFlowRevenueReport> result = this.entityManager.createQuery(query,
                SystemCashFlowRevenueReport.class);
        List<SystemCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate,
            LocalDate endDate) {

        String query = "FROM SystemCashFlowRevenueReport s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<SystemCashFlowRevenueReport> result = this.entityManager
                .createQuery(query, SystemCashFlowRevenueReport.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<SystemCashFlowRevenueReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<UsersCountReport> getUsersCountReport() {

        String query = "FROM UsersCountReport u";
        TypedQuery<UsersCountReport> result = this.entityManager.createQuery(query, UsersCountReport.class);
        List<UsersCountReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public List<StoresCountReport> getStoresCountReport() {

        String query = "FROM StoresCountReport s";
        TypedQuery<StoresCountReport> result = this.entityManager.createQuery(query,
                StoresCountReport.class);
        List<StoresCountReport> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }
}
