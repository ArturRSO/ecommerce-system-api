package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.Address;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.services.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createAddress(@RequestBody Address address, @RequestParam("user") boolean relateWithUser) {

        BaseResponseDTO<?> response;

        try {

            int addressId = this.addressService.createAddress(address, relateWithUser);

            response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), addressId);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

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

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getAdressesByUserId(@PathVariable("userId") int userId) {

        BaseResponseDTO<?> response;

        try {

            List<Address> adresses = this.addressService.getAdressesByUserId(userId);

            if (adresses == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), adresses);
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
    public ResponseEntity<?> getAddressById(@PathVariable("id") int id) {

        BaseResponseDTO<?> response;

        try {

            Address address = this.addressService.getAdressById(id);

            if (address == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), address);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateAddress(@RequestBody Address address) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

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

    @DeleteMapping("delete/{addressId}")
    public ResponseEntity<?> deleteAddresses(@PathVariable("addressId") int addressId) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.addressService.deleteAdress(addressId);

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
