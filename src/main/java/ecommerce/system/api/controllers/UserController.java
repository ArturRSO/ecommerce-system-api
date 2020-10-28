package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InactiveAccountException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.exceptions.InvalidTokenException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.models.UserOptionModel;
import ecommerce.system.api.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            response.setMessage("Usuário cadastrado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (InactiveAccountException iae) {

            logger.error(iae.getMessage());

            response.setSuccess(false);
            response.setMessage(iae.getMessage());
            response.setData("Deseja reativar a conta?");

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

    @PostMapping("/create/customer")
    public ResponseEntity<?> createCustomer(@RequestBody UserModel user) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.createCustomer(user);

            response.setSuccess(true);
            response.setMessage("Cadastro concluído com sucesso! Faça login para utilizar o sistema.");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (InactiveAccountException iae) {

            logger.error(iae.getMessage());

            response.setSuccess(false);
            response.setMessage(iae.getMessage());
            response.setData("Deseja reativar a conta?");

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {

        BaseResponseModel<?> response;

        try {

            List<UserModel> users = this.userService.getAllUsers();

            if (users == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), users);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> getUsersByRoleId(@PathVariable("roleId") int roleId) {

        BaseResponseModel<?> response;

        try {

            List<UserModel> users = this.userService.getUsersByRoleId(roleId);

            if (users == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), users);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {

        BaseResponseModel<?> response;

        try {

            UserModel user = this.userService.getUserById(id);

            if (user == null) {
                response = new BaseResponseModel<>(true, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), user);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {

        BaseResponseModel<?> response;

        try {

            UserModel user = this.userService.getUserProfile();

            if (user == null) {
                response = new BaseResponseModel<>(true, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), user);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recover/password/status/{token}")
    public ResponseEntity<?> getPasswordRecoverTokenStatus(@PathVariable("token") String token) {

        try {

          boolean status = this.userService.checkPasswordRecoverToken(token);

          BaseResponseModel<Boolean> response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), status);

          return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/options/{roleId}")
    public ResponseEntity<?> getUserOptionsByRoleId(@PathVariable("roleId") int roleId) {

        BaseResponseModel<?> response;

        try {

            List<UserOptionModel> options = this.userService.getUserOptionsByRoleId(roleId);

            if (options == null) {
                response = new BaseResponseModel<>(true, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), options);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/password/{userId}")
    public ResponseEntity<?> updateUserPassword(@PathVariable("userId") int userId, @RequestBody String password) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.updateUserPassword(false, userId, password);

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

    @PutMapping("update/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserModel user) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.updateUserProfile(user);

            response.setSuccess(true);
            response.setMessage("Perfil atualizado com sucesso!");
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

    @PostMapping("recover/password/{token}")
    public ResponseEntity<?> recoverPassword(@RequestBody String password, @PathVariable String token) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.recoverPassword(password, token);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidTokenException ite) {

            logger.error(ite.getMessage());

            response.setSuccess(false);
            response.setMessage(ite.getMessage());
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

    @PostMapping("recover/password/mail")
    public ResponseEntity<?> sendRecoverPasswordMail(@RequestBody String email) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            boolean result = this.userService.sendPasswordRecoverEmail(email);
            String message = result ? "E-mail para recuperação de senha enviado com sucesso!" :
                    "Nenhum cadastro relacionado a esse e-mail foi encontrado";

            response.setSuccess(result);
            response.setMessage(message);
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/profile/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable int id) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.deleteUserProfile(id);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (InvalidOperationException ioe) {

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
    public ResponseEntity<?> deleteUsers(@RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.deleteUsers(ids);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            String message = e.getClass() == BatchUpdateException.class ? e.getMessage() : MessagesEnum.FAILURE.getMessage();

            response.setSuccess(false);
            response.setMessage(message);
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
