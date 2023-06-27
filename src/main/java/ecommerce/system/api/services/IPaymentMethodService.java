package ecommerce.system.api.services;

import ecommerce.system.api.models.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {

    List<PaymentMethod> getPaymentMethods();
}
