package ecommerce.system.api.dto;

import java.util.Map;

public class PaymentMethodDTO {

    private int paymentMethodId;
    private Map<String, String> data;

    public PaymentMethodDTO(int paymentMethodId, Map<String, String> data) {
        this.paymentMethodId = paymentMethodId;
        this.data = data;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
