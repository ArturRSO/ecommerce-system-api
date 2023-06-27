package ecommerce.system.api.services.implementations;

import ecommerce.system.api.dto.PaymentMethodDTO;
import ecommerce.system.api.enums.PaymentMethodEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.CreditCard;
import ecommerce.system.api.models.PaymentMethod;
import ecommerce.system.api.services.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService implements IPaymentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean pay(PaymentMethodDTO data, double value) throws InvalidOperationException {

        PaymentMethod paymentMethod = this.paymentMethodFactory(data);

        logger.info("Processing payment with value " + value + " and payment method " + paymentMethod.getName());

        return true;
    }

    private PaymentMethod paymentMethodFactory(PaymentMethodDTO data) throws InvalidOperationException {

        PaymentMethodEnum paymentMethod = PaymentMethodEnum.getPaymentMethodById(data.getPaymentMethodId());
        Map<String, String> paymentMethodData = data.getData();

        switch (paymentMethod) {
            case CREDIT_CARD:
                return new CreditCard(
                        paymentMethodData.get("cardNumber"),
                        paymentMethodData.get("ownerName"),
                        paymentMethodData.get("expirationDate"),
                        paymentMethodData.get("securityCode"));
            default:
                throw new InvalidOperationException("Método de pagamento inválido!");
        }
    }
}
