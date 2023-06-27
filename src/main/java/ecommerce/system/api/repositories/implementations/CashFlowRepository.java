package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.StoreCashFlow;
import ecommerce.system.api.models.SystemCashFlow;
import ecommerce.system.api.repositories.ICashFlowRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class CashFlowRepository implements ICashFlowRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int createSystemCashFlowRecord(int orderId, double value) {

        SystemCashFlow systemCashFlow = new SystemCashFlow(orderId, value, LocalDateTime.now());

        this.entityManager.persist(systemCashFlow);
        this.entityManager.flush();

        return systemCashFlow.getSystemCashFlowId();
    }

    @Override
    public int createStoreCashFlowRecord(int storeId, int orderId, double value) {

        StoreCashFlow storeCashFlow = new StoreCashFlow(storeId, orderId, value, LocalDateTime.now());

        this.entityManager.persist(storeCashFlow);
        this.entityManager.flush();

        return storeCashFlow.getStoreCashFlowId();
    }
}
