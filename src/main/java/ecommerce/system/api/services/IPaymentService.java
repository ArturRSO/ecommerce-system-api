package ecommerce.system.api.services;

import ecommerce.system.api.dto.PaymentMethodDTO;
import ecommerce.system.api.exceptions.InvalidOperationException;

public interface IPaymentService {

    boolean pay(PaymentMethodDTO data, double value) throws InvalidOperationException;
}
