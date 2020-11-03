package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.AddressModel;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.services.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@RequestBody AddressModel address) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.addressService.createAddress(address);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
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

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdresses() {

        BaseResponseModel<?> response;

        try {

            List<AddressModel> adresses = this.addressService.getAllAdresses();

            if (adresses == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), adresses);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity<?> getAdressesByUserId(@PathVariable("userId") int userId) {

        BaseResponseModel<?> response;

        try {

            List<AddressModel> adresses = this.addressService.getAdressesByUserId(userId);

            if (adresses == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), adresses);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/profile/{userId}")
    public ResponseEntity<?> getProfileAdresses(@PathVariable("userId") int userId) {

        BaseResponseModel<?> response;

        try {

            List<AddressModel> adresses = this.addressService.getProfileAdresses(userId);

            if (adresses == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), adresses);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response = new BaseResponseModel<>(false, ioe.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") int id) {

        BaseResponseModel<?> response;

        try {

            AddressModel address = this.addressService.getAdressById(id);

            if (address == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), address);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressModel address) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.addressService.updateAddress(address);

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

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAddresses(@RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.addressService.deleteAdresses(ids);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            String message = e.getClass() == BatchUpdateException.class ? e.getMessage() : MessagesEnum.FAILURE.getMessage();

            response.setSuccess(false);
            response.setMessage(message);
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
