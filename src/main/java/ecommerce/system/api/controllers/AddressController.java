package ecommerce.system.api.controllers;

import ecommerce.system.api.exceptions.EmptySearchException;
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

@RestController
@RequestMapping("/adresses")
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
            response.setMessage("Endereço criado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdresses() {

        try {

            ArrayList<AddressModel> adresses = this.addressService.getAllAdresses();

            BaseResponseModel<ArrayList<AddressModel>> response = new BaseResponseModel<>(true, "Operação concluída com sucesso!", adresses);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity<?> getAdressesByUserId(@PathVariable("userId") int userId) {

        try {

            ArrayList<AddressModel> adresses = this.addressService.getAdressesByUserId(userId);

            BaseResponseModel<ArrayList<AddressModel>> response = new BaseResponseModel<>(true, "Operação concluída com sucesso", adresses);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") int id) {

        try {

            AddressModel address = this.addressService.getAdressById(id);

            BaseResponseModel<AddressModel> response = new BaseResponseModel<>(true, "Endereço encontrado com sucesso", address);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressModel address) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.addressService.updateAddress(address);

            response.setSuccess(true);
            response.setMessage("Endereço atualizado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAddresses(@RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.addressService.deleteAdresses(ids);

            String message = ids.size() > 1 ? "Endereços deletados com sucesso!" : "Endereço deletado com sucesso!";

            response.setSuccess(true);
            response.setMessage(message);
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
