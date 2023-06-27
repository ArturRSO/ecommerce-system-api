package ecommerce.system.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "PaymentMethod")
@Table(name = "tb_paymentMethod")
public class PaymentMethod {

    @Id
    @Column(name = "pk_paymentMethodId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentMethodId;

    @Column(name = "name")
    private String name;

    @Column(name = "maxInstallment")
    private int maxInstallment;

    @Column(name = "isEnable")
    @JsonIgnore
    private boolean enable;

    public PaymentMethod(int paymentMethodId, String name, int maxInstallment, boolean enable) {
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
