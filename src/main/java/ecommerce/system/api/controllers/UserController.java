package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.exceptions.InvalidTokenException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.User;
import ecommerce.system.api.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        BaseResponseDTO<?> response;

        try {

            int userId = this.userService.createUser(user);

            response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), userId);

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

    @PostMapping("create/image/{userId}")
    public ResponseEntity<?> createProfileImage(@PathVariable("userId") int userId,
            @RequestParam("file") MultipartFile file) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.userService.createProfileImage(file, userId);

            response.setSuccess(true);
            response.setMessage("Imagem cadastrada com sucesso!");
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

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers() {

        BaseResponseDTO<?> response;

        try {

            List<User> users = this.userService.getAllUsers();

            if (users == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), users);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("role/{roleId}")
    public ResponseEntity<?> getUsersByRoleId(@PathVariable("roleId") int roleId) {

        BaseResponseDTO<?> response;

        try {

            List<User> users = this.userService.getUsersByRoleId(roleId);

            if (users == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), users);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {

        BaseResponseDTO<?> response;

        try {

            User user = this.userService.getUserById(id, false);

            if (user == null) {
                response = new BaseResponseDTO<>(true, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), user);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("profile")
    public ResponseEntity<?> getProfile() {

        BaseResponseDTO<?> response;

        try {

            User user = this.userService.getUserProfile();

            if (user == null) {
                response = new BaseResponseDTO<>(true, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), user);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("recover/password/status")
    public ResponseEntity<?> getPasswordRecoverTokenStatus(@RequestParam("token") String token) {

        try {

            boolean status = this.userService.checkPasswordRecoverToken(token);

            BaseResponseDTO<Boolean> response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), status);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseDTO<String> response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/password/{userId}")
    public ResponseEntity<?> updateUserPassword(@PathVariable("userId") int userId, @RequestBody String password) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

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

    @PutMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.userService.updateUser(user);

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

    @PutMapping("recover/password")
    public ResponseEntity<?> recoverPassword(@RequestBody String password, @RequestParam String token) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.userService.recoverPassword(password, token);

            response.setSuccess(true);
            response.setMessage("Senha atualizada com sucesso! Faça login para utilizar o sistema.");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException | InvalidTokenException ie) {

            logger.error(ie.getMessage());

            response.setSuccess(false);
            response.setMessage(ie.getMessage());
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
    public ResponseEntity<?> sendPasswordRecoverMail(@RequestBody String email) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            boolean result = this.userService.sendPasswordRecoverEmail(email);
            String message = result ? "E-mail para recuperação de senha enviado com sucesso!"
                    : "Nenhum cadastro relacionado a esse e-mail foi encontrado";

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

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.userService.deleteUser(userId);

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
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
