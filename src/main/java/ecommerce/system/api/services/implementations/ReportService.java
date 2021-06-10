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
    public List<OrdersByStoreReportModel> getOrdersReport() {
        return this.reportRepository.getOrdersReport();
    }

    @Override
    public OrdersByStoreReportModel getOrdersReportByStoreId(int storeId) {
        return this.reportRepository.getOrdersReportByStoreId(storeId);
    }

    @Override
    public List<ProductsByStoreReportModel> getProductsReport() {
        return this.reportRepository.getProductsReport();
    }

    @Override
    public ProductsByStoreReportModel getProductsReportByStoreId(int storeId) {
        return this.reportRepository.getProductsReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReport() {
        return this.reportRepository.getStoreCashFlowReport();
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByDateRange(LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowReportByDateRange(startDate, endDate);
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreId(int storeId) {
        return this.reportRepository.getStoreCashFlowReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowByOrderReportModel> getStoreCashFlowReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowReportByStoreIdAndDateRange(storeId, startDate, endDate);
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReport() {
        return this.reportRepository.getStoreCashFlowRevenueReport();
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowRevenueReportByDateRange(startDate, endDate);
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreId(int storeId) {
        return this.reportRepository.getStoreCashFlowRevenueReportByStoreId(storeId);
    }

    @Override
    public List<StoreCashFlowRevenueReportModel> getStoreCashFlowRevenueReportByStoreIdAndDateRange(int storeId, LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getStoreCashFlowRevenueReportByStoreIdAndDateRange(storeId, startDate, endDate);
    }

    @Override
    public List<StoresByUserReportModel> getStoresByUserReport() {
        return this.reportRepository.getStoresByUserReport();
    }

    @Override
    public StoresByUserReportModel getStoresByUserReportByUserId(int userId) {
        return this.reportRepository.getStoresByUserReportByUserId(userId);
    }

    @Override
    public List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReport() {
        return this.reportRepository.getSystemCashFlowByOrderReport();
    }

    @Override
    public List<SystemCashFlowByOrderReportModel> getSystemCashFlowByOrderReportByDateRange(LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getSystemCashFlowByOrderReportByDateRange(startDate, endDate);
    }

    @Override
    public List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReport() {
        return this.reportRepository.getSystemCashFlowRevenueReport();
    }

    @Override
    public List<SystemCashFlowRevenueReportModel> getSystemCashFlowRevenueReportByDateRange(LocalDate startDate, LocalDate endDate) {
        return this.reportRepository.getSystemCashFlowRevenueReportByDateRange(startDate, endDate);
    }

    @Override
    public List<UsersCountReportModel> getUsersCountReport() {
        return this.reportRepository.getUsersCountReport();
    }

    @Override
    public List<StoresCountReportModel> getStoresCountReport() {

        return this.reportRepository.getStoresCountReport();
    }
}
