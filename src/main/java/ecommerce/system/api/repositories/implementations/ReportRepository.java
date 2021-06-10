package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.*;
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
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class ReportRepository implements IReportRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OrdersByStoreReportModel> getOrdersReport() {

        String query = "FROM OrdersByStoreReportEntity o";
        TypedQuery<OrdersByStoreReportEntity> result = this.entityManager.createQuery(query, OrdersByStoreReportEntity.class);
        List<OrdersByStoreReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<OrdersByStoreReportModel> ordersByStoreReports = new ArrayList<>();
        (entities).forEach(entity -> ordersByStoreReports.add(entity.toModel()));

        return ordersByStoreReports;
    }

    @Override
    public OrdersByStoreReportModel getOrdersReportByStoreId(int storeId) {

        try {

            String query = "FROM OrdersByStoreReportEntity o WHERE o.storeId = :storeId";
            TypedQuery<OrdersByStoreReportEntity> result = this.entityManager.createQuery(query, OrdersByStoreReportEntity.class)
                    .setParameter("storeId", storeId);
            OrdersByStoreReportEntity ordersByStoreReport = result.getSingleResult();

            return ordersByStoreReport.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<ProductsByStoreReportModel> getProductsReport() {

        String query = "FROM ProductsByStoreReportEntity p";
        TypedQuery<ProductsByStoreReportEntity> result = this.entityManager.createQuery(query, ProductsByStoreReportEntity.class);
        List<ProductsByStoreReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductsByStoreReportModel> productsByStoreReports = new ArrayList<>();
        (entities).forEach(entity -> productsByStoreReports.add(entity.toModel()));

        return productsByStoreReports;
    }

    @Override
    public ProductsByStoreReportModel getProductsReportByStoreId(int storeId) {

        try {

            String query = "FROM ProductsByStoreReportEntity p WHERE p.storeId = :storeId";
            TypedQuery<ProductsByStoreReportEntity> result = this.entityManager.createQuery(query, ProductsByStoreReportEntity.class)
                    .setParameter("storeId", storeId);
            ProductsByStoreReportEntity productsByStoreReport = result.getSingleResult();

            return productsByStoreReport.toModel();

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReport() {

        String query = "FROM StoreCashFlowReportEntity s";
        TypedQuery<StoreCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowByOrderReportEntity.class);
        List<StoreCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowByOrderReportModel> storeCashFlowReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowReports.add(entity.toModel()));

        return storeCashFlowReports;
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByDateRange(LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<StoreCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowByOrderReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<StoreCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowByOrderReportModel> storeCashFlowReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowReports.add(entity.toModel()));

        return storeCashFlowReports;
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreId(int storeId) {

        String query = "FROM StoreCashFlowReportEntity s WHERE s.storeId = :storeId";
        TypedQuery<StoreCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowByOrderReportEntity.class)
                .setParameter("storeId", storeId);
        List<StoreCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowByOrderReportModel> storeCashFlowReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowReports.add(entity.toModel()));

        return storeCashFlowReports;
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate AND s.storeId = :storeId";
        TypedQuery<StoreCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowByOrderReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("storeId", storeId);
        List<StoreCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowByOrderReportModel> storeCashFlowReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowReports.add(entity.toModel()));

        return storeCashFlowReports;
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReport() {

        String query = "FROM StoreCashFlowRevenueReportEntity s";
        TypedQuery<StoreCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowRevenueReportEntity.class);
        List<StoreCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowRevenueReportModel> storeCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowRevenueReports.add(entity.toModel()));

        return storeCashFlowRevenueReports;
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowRevenueReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<StoreCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowRevenueReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<StoreCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowRevenueReportModel> storeCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowRevenueReports.add(entity.toModel()));

        return storeCashFlowRevenueReports;
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreId(int storeId) {

        String query = "FROM StoreCashFlowRevenueReportEntity s WHERE s.storeId = :storeId";
        TypedQuery<StoreCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowRevenueReportEntity.class)
                .setParameter("storeId", storeId);
        List<StoreCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowRevenueReportModel> storeCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowRevenueReports.add(entity.toModel()));

        return storeCashFlowRevenueReports;
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate) {

        String query = "FROM StoreCashFlowRevenueReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate AND s.storeId = :storeId";
        TypedQuery<StoreCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, StoreCashFlowRevenueReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("storeId", storeId);
        List<StoreCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoreCashFlowRevenueReportModel> storeCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> storeCashFlowRevenueReports.add(entity.toModel()));

        return storeCashFlowRevenueReports;
    }

    @Override
    public List<StoresByUserReportModel> getStoresByUserReport() {

        String query = "FROM StoresByUserReportEntity s";
        TypedQuery<StoresByUserReportEntity> result = this.entityManager.createQuery(query, StoresByUserReportEntity.class);
        List<StoresByUserReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoresByUserReportModel> storesByUserReports = new ArrayList<>();
        (entities).forEach(entity -> storesByUserReports.add(entity.toModel()));

        return storesByUserReports;
    }

    @Override
    public StoresByUserReportModel getStoresByUserReportByUserId(int userId) {

        try {

            String query = "FROM StoresByUserReportEntity s WHERE s.userId = :userId";
            TypedQuery<StoresByUserReportEntity> result = this.entityManager.createQuery(query, StoresByUserReportEntity.class)
                    .setParameter("userId", userId);
            StoresByUserReportEntity storesByUserReport = result.getSingleResult();

            return storesByUserReport.toModel();

        }  catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReport() {

        String query = "FROM SystemCashFlowByOrderReportEntity s";
        TypedQuery<SystemCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, SystemCashFlowByOrderReportEntity.class);
        List<SystemCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<SystemCashFlowByOrderReportModel> systemCashFlowByOrderReports = new ArrayList<>();
        (entities).forEach(entity -> systemCashFlowByOrderReports.add(entity.toModel()));

        return systemCashFlowByOrderReports;
    }

    @Override
    public List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate, LocalDate endDate) {

        String query = "FROM SystemCashFlowByOrderReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<SystemCashFlowByOrderReportEntity> result = this.entityManager.createQuery(query, SystemCashFlowByOrderReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<SystemCashFlowByOrderReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<SystemCashFlowByOrderReportModel> systemCashFlowByOrderReports = new ArrayList<>();
        (entities).forEach(entity -> systemCashFlowByOrderReports.add(entity.toModel()));

        return systemCashFlowByOrderReports;
    }

    @Override
    public List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReport() {

        String query = "FROM SystemCashFlowRevenueReportEntity s";
        TypedQuery<SystemCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, SystemCashFlowRevenueReportEntity.class);
        List<SystemCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<SystemCashFlowRevenueReportModel> systemCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> systemCashFlowRevenueReports.add(entity.toModel()));

        return systemCashFlowRevenueReports;
    }

    @Override
    public List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate) {

        String query = "FROM SystemCashFlowRevenueReportEntity s WHERE CAST(s.timestamp as LocalDate) BETWEEN :startDate AND :endDate";
        TypedQuery<SystemCashFlowRevenueReportEntity> result = this.entityManager.createQuery(query, SystemCashFlowRevenueReportEntity.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        List<SystemCashFlowRevenueReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<SystemCashFlowRevenueReportModel> systemCashFlowRevenueReports = new ArrayList<>();
        (entities).forEach(entity -> systemCashFlowRevenueReports.add(entity.toModel()));

        return systemCashFlowRevenueReports;
    }

    @Override
    public List<UsersCountReportModel> getUsersCountReport() {

        String query = "FROM UsersCountReportEntity u";
        TypedQuery<UsersCountReportEntity> result = this.entityManager.createQuery(query, UsersCountReportEntity.class);
        List<UsersCountReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<UsersCountReportModel> usersCountReports = new ArrayList<>();
        (entities).forEach(entity -> usersCountReports.add(entity.toModel()));

        return usersCountReports;
    }

    @Override
    public List<StoresCountReportModel> getStoresCountReport() {

        String query = "FROM StoresCountReportEntity s";
        TypedQuery<StoresCountReportEntity> result = this.entityManager.createQuery(query, StoresCountReportEntity.class);
        List<StoresCountReportEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<StoresCountReportModel> storesCountReports = new ArrayList<>();
        (entities).forEach(entity -> storesCountReports.add(entity.toModel()));

        return storesCountReports;
    }
}
