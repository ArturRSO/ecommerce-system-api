package ecommerce.system.api.dto;

public class PaymentDTO {

    private PaymentMethodDTO paymentMethod;
    private double value;

    public PaymentDTO(PaymentMethodDTO paymentMethod, double value) {
        this.paymentMethod = paymentMethod;
        this.value = value;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
