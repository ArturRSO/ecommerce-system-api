package ecommerce.system.api.controllers;

import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.models.PaymentMethodModel;
import ecommerce.system.api.services.IPaymentMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("paymentmethods")
public class PaymentMethodController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IPaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(IPaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("all")
    public ResponseEntity<?> getPaymentMethods() {

        BaseResponseDTO<?> response;

        try {

            List<PaymentMethodModel> paymentMethods = this.paymentMethodService.getPaymentMethods();

            if (paymentMethods == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), paymentMethods);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
