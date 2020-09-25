package ecommerce.system.api.controllers;

import ecommerce.system.api.exceptions.EmptySearchException;
import ecommerce.system.api.exceptions.ForbiddenException;
import ecommerce.system.api.exceptions.InvalidTokenException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.CredentialsModel;
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
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/customer")
    public ResponseEntity<?> createCustomer(@RequestBody UserModel user) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.createCustomer(user);

            response.setSuccess(true);
            response.setMessage("Usuário criado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (ForbiddenException fe) {

            logger.error(fe.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(fe.getMessage());

            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
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

            HttpStatus httpStatus = e.getClass() == EmptySearchException.class ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

            return new ResponseEntity<>(response, httpStatus);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {

        try {

            UserModel user = this.userService.getUserById(id);

            BaseResponseModel<UserModel> response = new BaseResponseModel<>(true, "Usuário encontrado com sucesso", user);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {

        try {

            UserModel user = this.userService.getProfile();

            BaseResponseModel<UserModel> response = new BaseResponseModel<>(true, "Perfil encontrado com sucesso", user);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recover/password/status/{token}")
    public ResponseEntity<?> getPasswordRecoverTokenStatus(@PathVariable("token") String token) {

        try {

          boolean status =  this.userService.checkPasswordRecoverToken(token);

          BaseResponseModel<Boolean> response = new BaseResponseModel<>(true, "Status do token verificado com sucesso!", status);

          return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Ocorreu um erro.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserModel user) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.updateUser(user);

            response.setSuccess(true);
            response.setMessage("Usuário atualizado com sucesso!");
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

    @PutMapping("update/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody UserModel user) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.updateUserPassword(false, user.getUserId(), user.getRoleId(), user.getEmail(), user.getPassword());

            response.setSuccess(true);
            response.setMessage("Senha atualizada com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ForbiddenException fe) {

            logger.error(fe.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(fe.getMessage());

            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("recover/password/{token}")
    public ResponseEntity<?> recoverPassword(@RequestBody String password, @PathVariable String token) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.recoverPassword(password, token);

            response.setSuccess(true);
            response.setMessage("Senha atualizada com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidTokenException ite) {

            logger.error(response.getMessage());

            response.setSuccess(false);
            response.setMessage("Token expirado!");
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


    @PostMapping("recover/password/mail")
    public ResponseEntity<?> sendRecoverPasswordMail(@RequestBody String email) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            boolean result = this.userService.sendPasswordRecoverEmail(email);
            String message = result ? "E-mail para recuperação de senha enviado com sucesso!" :
                    "Nenhum cadastro relacionando a esse e-mail foi encontrado";

            response.setSuccess(result);
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

    @DeleteMapping("/delete/profile/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable int id) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.deleteUserProfile(id);

            response.setSuccess(true);
            response.setMessage("Perfil deletado com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (ForbiddenException fe) {

            logger.error(fe.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(fe.getMessage());

            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage("Ocorreu um erro.");
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUsers(@RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.userService.deleteUsers(ids);

            String message = ids.size() > 1 ? "Usuários deletados com sucesso!" : "Usuário deletado com sucesso!";

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
