package ecommerce.system.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PaymentMethodModel {

    private int paymentMethodId;
    private String name;
    private int maxInstallment;

    @JsonIgnore
    private boolean enable;

    public PaymentMethodModel(int paymentMethodId, String name, int maxInstallment, boolean enable) {
        this.paymentMethodId = paymentMethodId;
        this.name = name;
        this.maxInstallment = maxInstallment;
        this.enable = enable;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxInstallment() {
        return maxInstallment;
    }

    public void setMaxInstallment(int maxInstallment) {
        this.maxInstallment = maxInstallment;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
