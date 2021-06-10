package ecommerce.system.api.services;

import ecommerce.system.api.models.*;

import java.time.LocalDate;
import java.util.List;

public interface IReportService {

    List<OrdersByStoreReportModel> getOrdersReport();
    OrdersByStoreReportModel getOrdersReportByStoreId(int storeId);
    List<ProductsByStoreReportModel> getProductsReport();
    ProductsByStoreReportModel getProductsReportByStoreId(int storeId);
    List<StoreCashFlowByOrderReportModel> getStoreCashFlowReport();
    List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByDateRange(LocalDate startDate, LocalDate endDate);
    List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreId(int storeId);
    List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate);
    List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReport();
    List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate);
    List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreId(int storeId);
    List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate);
    List<StoresByUserReportModel> getStoresByUserReport();
    StoresByUserReportModel getStoresByUserReportByUserId(int userId);
    List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReport();
    List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate, LocalDate endDate);
    List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReport();
    List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate);
    List<UsersCountReportModel> getUsersCountReport();
    List<StoresCountReportModel> getStoresCountReport();
}
