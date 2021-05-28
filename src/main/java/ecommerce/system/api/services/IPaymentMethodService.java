package ecommerce.system.api.services;

import ecommerce.system.api.models.PaymentMethodModel;

import java.util.List;

public interface IPaymentMethodService {

    List<PaymentMethodModel> getPaymentMethods();
}
