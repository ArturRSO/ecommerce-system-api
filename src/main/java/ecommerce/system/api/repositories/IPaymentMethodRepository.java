package ecommerce.system.api.repositories;

import ecommerce.system.api.models.PaymentMethod;

import java.util.List;

public interface IPaymentMethodRepository {

    List<PaymentMethod> getPaymentMethods();
}
