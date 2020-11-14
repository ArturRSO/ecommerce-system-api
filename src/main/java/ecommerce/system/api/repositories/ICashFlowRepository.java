package ecommerce.system.api.repositories;

public interface ICashFlowRepository {

    int createSystemCashFlowRecord(int orderId, double value);
    int createStoreCashFlowRecord(int storeId, int orderId, double value);
}
