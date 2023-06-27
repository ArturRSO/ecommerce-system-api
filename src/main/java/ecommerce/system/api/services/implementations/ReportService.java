package ecommerce.system.api.services.implementations;

import ecommerce.system.api.models.*;
import ecommerce.system.api.repositories.IReportRepository;
import ecommerce.system.api.services.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService implements IReportService {

    private final IReportRepository reportRepository;

    @Autowired
    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<OrdersByStoreReport> getOrdersReport() {
        return this.reportRepository.getOrdersReport();
    }

    @Override
    public OrdersByStoreReport getOrdersReportByStoreId(int storeId) {
        return this.reportRepository.getOrdersReportByStoreId(storeId);
    }

    @Override
    public List<ProductsByStoreReport> getProductsReport() {
        return this.reportRepository.getProductsReport();
    }

    @Override
    public ProductsByStoreReport getProductsReportByStoreId(int storeId) {
        return this.reportRepository.getProductsReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReport() {
        return this.reportRepository.getStoreCashFlowReport();
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByDateRange(LocalDate startDate,
            LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowReportByDateRange(startDate, endDate);
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreId(int storeId) {
        return this.reportRepository.getStoreCashFlowReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowByOrderReport> getStoreCashFlowReportByStoreIdAndDateRange(int storeId,
            LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowReportByStoreIdAndDateRange(storeId, startDate, endDate);
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReport() {
        return this.reportRepository.getStoreCashFlowRevenueReport();
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate,
            LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowRevenueReportByDateRange(startDate, endDate);
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreId(int storeId) {
        return this.reportRepository.getStoreCashFlowRevenueReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowRevenueReport> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId,
            LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowRevenueReportByStoreIdAndDateRange(storeId, startDate, endDate);
    }

    @Override
    public List<StoresByUserReport> getStoresByUserReport() {
        return this.reportRepository.getStoresByUserReport();
    }

    @Override
    public StoresByUserReport getStoresByUserReportByUserId(int userId) {
        return this.reportRepository.getStoresByUserReportByUserId(userId);
    }

    @Override
    public List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReport() {
        return this.reportRepository.getSystemCashFlowByOrderReport();
    }

    @Override
    public List<SystemCashFlowByOrderReport> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate,
            LocalDate endDate) {
        return this.reportRepository.getSystemCashFlowByOrderReportByDateRange(startDate, endDate);
    }

    @Override
    public List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReport() {
        return this.reportRepository.getSystemCashFlowRevenueReport();
    }

    @Override
    public List<SystemCashFlowRevenueReport> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate,
            LocalDate endDate) {
        return this.reportRepository.getSystemCashFlowRevenueReportByDateRange(startDate, endDate);
    }

    @Override
    public List<UsersCountReport> getUsersCountReport() {
        return this.reportRepository.getUsersCountReport();
    }

    @Override
    public List<StoresCountReport> getStoresCountReport() {

        return this.reportRepository.getStoresCountReport();
    }
}
