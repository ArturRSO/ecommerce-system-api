package ecommerce.system.api.controllers;

import ecommerce.system.api.dto.PaymentDTO;
import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.OrderModel;
import ecommerce.system.api.services.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.orderService.createOrder(order);

            response.setSuccess(true);
            response.setMessage("Pedido gerado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response.setSuccess(false);
            response.setMessage(ioe.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("store/{storeId}")
    public ResponseEntity<?> getOrdersByStoreId(@PathVariable("storeId") int storeId) {

        BaseResponseDTO<?> response;

        try {

            List<OrderModel> orders = this.orderService.getOrdersByStoreId(storeId);

            if (orders == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), orders);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable("userId") int userId) {

        BaseResponseDTO<?> response;

        try {

            List<OrderModel> orders = this.orderService.getOrderSummariesByUserId(userId);

            if (orders == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), orders);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{orderId}/status/{statusId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("orderId") int orderSummaryId, @PathVariable("statusId") int orderStatusId) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.orderService.updateOrderStatus(orderSummaryId, orderStatusId);

            response.setSuccess(true);
            response.setMessage("Pedido atualizado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response.setSuccess(false);
            response.setMessage(ioe.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{orderId}/pay")
    public ResponseEntity<?> payOrder(@PathVariable("orderId") int orderSummaryId, @RequestBody PaymentDTO paymentInfo) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.orderService.payOrder(orderSummaryId, paymentInfo);

            response.setSuccess(true);
            response.setMessage("Pedido atualizado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response.setSuccess(false);
            response.setMessage(ioe.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
