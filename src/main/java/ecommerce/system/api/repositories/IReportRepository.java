package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.time.LocalDate;
import java.util.List;

public interface IReportRepository {

        List<OrdersByStoreReport> getOrdersReport();

        OrdersByStoreReport getOrdersReportByStoreId(int storeId);

        List<ProductsByStoreReport> getProductsReport();

        ProductsByStoreReport getProductsReportByStoreId(int storeId);

        List<StoreCashFlowByOrderReport> getStoreCashFlowReport();

        List<StoreCashFlowByOrderReport> getStoreCashFlowReportByDateRange(LocalDate startDate, LocalDate endDate);

        List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreId(int storeId);

        List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreIdAndDateRange(int storeId,
                        LocalDate startDate,
                        LocalDate endDate);

        List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReport();

        List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate,
                        LocalDate endDate);

        List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreId(int storeId);

        List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId,
                        LocalDate startDate, LocalDate endDate);

        List<StoresByUserReport> getStoresByUserReport();

        StoresByUserReport getStoresByUserReportByUserId(int userId);

        List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReport();

        List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate,
                        LocalDate endDate);

        List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReport();

        List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate,
                        LocalDate endDate);

        List<UsersCountReport> getUsersCountReport();

        List<StoresCountReport> getStoresCountReport();
}
