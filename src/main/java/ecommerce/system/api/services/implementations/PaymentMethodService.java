package ecommerce.system.api.services.implementations;

import ecommerce.system.api.models.PaymentMethod;
import ecommerce.system.api.repositories.IPaymentMethodRepository;
import ecommerce.system.api.services.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    private final IPaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(IPaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<PaymentMethod> getPaymentMethods() {

        return this.paymentMethodRepository.getPaymentMethods();
    }
}
