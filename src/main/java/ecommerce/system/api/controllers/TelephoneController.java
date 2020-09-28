package ecommerce.system.api.controllers;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.TelephoneModel;
import ecommerce.system.api.services.ITelephoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/telephones")
public class TelephoneController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ITelephoneService telephoneService;

    @Autowired
    public TelephoneController(ITelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTelephone(@RequestBody TelephoneModel telephone) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.telephoneService.createTelephone(telephone);

            response.setSuccess(true);
            response.setMessage("Telefone criado com sucesso!");
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
    public ResponseEntity<?> getAllTelephones() {

        try {

            List<TelephoneModel> telephones = this.telephoneService.getAllTelephones();

            BaseResponseModel<List<TelephoneModel>> response = new BaseResponseModel<>(true, "Operação concluída com sucesso!", telephones);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity<?> getTelephonesByUserId(@PathVariable("userId") int userId) {

        try {

            List<TelephoneModel> telephones = this.telephoneService.getTelephonesByUserId(userId);

            BaseResponseModel<List<TelephoneModel>> response = new BaseResponseModel<>(true, "Operação concluída com sucesso!", telephones);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTelephoneById(@PathVariable("id") int id) {

        try {

            TelephoneModel telephone = this.telephoneService.getTelephoneById(id);

            BaseResponseModel<TelephoneModel> response = new BaseResponseModel<>(true, "Telefone encontrado com sucesso", telephone);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTelephone(@RequestBody TelephoneModel telephone) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.telephoneService.updateTelephone(telephone);

            response.setSuccess(true);
            response.setMessage("Telefone atualizado com sucesso!");
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
    public ResponseEntity<?> deleteTelephones(@RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.telephoneService.deleteTelephones(ids);

            String message = ids.size() > 1 ? "Telefones deletados com sucesso!" : "Telefone deletado com sucesso!";

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
