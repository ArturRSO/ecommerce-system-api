package ecommerce.system.api.repositories;

import ecommerce.system.api.models.PaymentMethodModel;

import java.util.List;

public interface IPaymentMethodRepository {

    List<PaymentMethodModel> getPaymentMethods();
}
