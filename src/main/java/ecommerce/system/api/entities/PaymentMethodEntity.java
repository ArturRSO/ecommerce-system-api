package ecommerce.system.api.entities;

import ecommerce.system.api.models.PaymentMethodModel;

import javax.persistence.*;

@Entity(name = "PaymentMethodEntity")
@Table(name = "tb_paymentMethod")
public class PaymentMethodEntity {

    @Id
    @Column(name = "pk_paymentMethodId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentMethodId;

    @Column(name = "name")
    private String name;

    @Column(name = "maxInstallment")
    private int maxInstallment;

    @Column(name = "isEnable")
    private boolean enable;

    public PaymentMethodEntity() {
    }

    public PaymentMethodEntity(PaymentMethodModel paymentMethod) {
        this.paymentMethodId = paymentMethod.getPaymentMethodId();
        this.name = paymentMethod.getName();
        this.maxInstallment = paymentMethod.getMaxInstallment();
        this.enable = paymentMethod.isEnable();
    }

    public PaymentMethodModel toModel() {
        return new PaymentMethodModel(
                this.paymentMethodId,
                this.name,
                this.maxInstallment,
                this.enable
        );
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
