package ecommerce.system.api.controllers;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.createUser(user);

            response.setSuccess(true);
            response.setMessage("Usuário criado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Um erro ocorreu.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {

        try {

            ArrayList<UserModel> users = this.userService.getAllUsers();

            BaseResponseModel<ArrayList<UserModel>> response = new BaseResponseModel<>(true, "Operação concluída com sucesso!", users);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }
}
