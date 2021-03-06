package ecommerce.system.api.services;

import ecommerce.system.api.dto.PaymentMethodDTO;
import ecommerce.system.api.models.PaymentMethodModel;

public interface IPaymentService {

    PaymentMethodModel paymentMethodFactory(PaymentMethodDTO data);
    boolean pay(PaymentMethodModel paymentMethod, double value);
}
