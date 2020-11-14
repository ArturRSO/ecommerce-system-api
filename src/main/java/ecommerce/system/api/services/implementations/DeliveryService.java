package ecommerce.system.api.services.implementations;

import ecommerce.system.api.models.DeliveryModel;
import ecommerce.system.api.repositories.IDeliveryRepository;
import ecommerce.system.api.services.IDeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {

    private final IDeliveryRepository deliveryRepository;

    public DeliveryService(IDeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void createDelivery(DeliveryModel delivery) {

        delivery.setSuccess(false);

        this.deliveryRepository.createDelivery(delivery);
    }

    @Override
    public double getDeliveryPrice() {

        return 10.00;
    }

    @Override
    public List<DeliveryModel> getDeliveriesByOrderId(int orderId) {

        return this.deliveryRepository.getDeliveriesByOrderId(orderId);
    }

    @Override
    public void updateDeliveryStatus(int deliveryId, boolean status) {

        this.deliveryRepository.updateDeliveryStatus(deliveryId, status);
    }
}
