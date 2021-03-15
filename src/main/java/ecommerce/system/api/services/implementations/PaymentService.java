package ecommerce.system.api.services.implementations;

import ecommerce.system.api.dto.PaymentMethodDTO;
import ecommerce.system.api.enums.PaymentMethodEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.CreditCardModel;
import ecommerce.system.api.models.PaymentMethodModel;
import ecommerce.system.api.services.IPaymentService;

import java.util.Map;

public class PaymentService implements IPaymentService {

    @Override
    public boolean pay(PaymentMethodDTO data, double value) throws InvalidOperationException {
        //TODO

        PaymentMethodModel paymentMethod = this.paymentMethodFactory(data);

        return true;
    }

    private PaymentMethodModel paymentMethodFactory(PaymentMethodDTO data) throws InvalidOperationException {

        PaymentMethodEnum paymentMethod = PaymentMethodEnum.getPaymentMethodById(data.getPaymentMethodId());
        Map<String, String> paymentMethodData = data.getData();

        switch (paymentMethod) {
            case CREDIT_CARD:
                return new CreditCardModel(
                        paymentMethodData.get("cardNumber"),
                        paymentMethodData.get("ownerName"),
                        paymentMethodData.get("expirationDate"),
                        paymentMethodData.get("securityCode"));
            default:
                throw new InvalidOperationException("Método de pagamento inválido!");
        }
    }
}
