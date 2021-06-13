package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.services.ITelephoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("telephones")
public class TelephoneController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ITelephoneService telephoneService;

    @Autowired
    public TelephoneController(ITelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createTelephone(@RequestBody TelephoneModel telephone, @RequestParam("user") boolean relateWithUser) {

        BaseResponseDTO<?> response;

        try {

            int telephoneId = this.telephoneService.createTelephone(telephone, relateWithUser);

            response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), telephoneId);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getTelephonesByUserId(@PathVariable("userId") int userId) {

        BaseResponseDTO<?> response;

        try {

            List<TelephoneModel> telephones = this.telephoneService.getTelephonesByUserId(userId);

            if (telephones == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), telephones);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response = new BaseResponseDTO<>(false, ioe.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTelephoneById(@PathVariable("id") int id) {

        BaseResponseDTO<?> response;

        try {

            TelephoneModel telephone = this.telephoneService.getTelephoneById(id);

            if (telephone == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), telephone);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTelephone(@RequestBody TelephoneModel telephone) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.telephoneService.updateTelephone(telephone);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
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

    @DeleteMapping("delete/{telephoneId}")
    public ResponseEntity<?> deleteTelephones(@PathVariable("telephoneId") int telephoneId) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.telephoneService.deleteTelephone(telephoneId);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
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
