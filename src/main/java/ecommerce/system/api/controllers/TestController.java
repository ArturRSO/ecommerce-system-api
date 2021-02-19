package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.enums.NotificationsEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.SimpleMailModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.services.IFileService;
import ecommerce.system.api.tools.EmailSender;
import ecommerce.system.api.tools.NotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmailSender emailSender;
    private final IFileService fileService;
    private final NotificationHandler notificationHandler;

    @Autowired
    public TestController(EmailSender emailSender, IFileService fileService, NotificationHandler notificationHandler) {
        this.emailSender = emailSender;
        this.fileService = fileService;
        this.notificationHandler = notificationHandler;

    }

    @GetMapping("auth")
    public ResponseEntity<?> checkAuth() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        BaseResponseDTO<Set<String>> response = new BaseResponseDTO<>(true, "", roles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("check")
    public ResponseEntity<?> checkApplication() {

        logger.info("Checking if API is on...");

        BaseResponseDTO<String> response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), "Hello world!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("error")
    public ResponseEntity<?> testError() {

        logger.info("Testing error...");

        BaseResponseDTO<String> response = new BaseResponseDTO<>(true, MessagesEnum.FAILURE.getMessage(), "Returning error for test purposes.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("mail")
    public ResponseEntity<?> simpleMailTest(@RequestBody SimpleMailModel mail) {

        logger.info("Testing mail...");

        try {
            this.emailSender.sendSimpleMail(mail);

            logger.info("Mail tested with success!");

            BaseResponseDTO<SimpleMailModel> response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), mail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseDTO<String> response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("mail/recover/password")
    public ResponseEntity<?> sendRecoverPasswordEmail(@RequestBody UserModel user) {

        logger.info("Testing recover password mail...");

        try {

            SimpleMailModel mail = this.notificationHandler.sendEmail(user.getUserId(), user.getEmail(), NotificationsEnum.PASSWORD_RECOVER, null);

            logger.info("Recover password mail tested with success!");

            BaseResponseDTO<SimpleMailModel> response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), mail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseDTO<String> response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("images")
    public ResponseEntity<?> getImage(@RequestParam("path") String path) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            String base64Image = this.fileService.getImageBase64(path);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData(base64Image);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("images/create/{object}/{id}")
    public ResponseEntity<?> createImage(@PathVariable("object") String object, @PathVariable("id") int id, @RequestParam("file") MultipartFile file) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            String path = this.fileService.saveMultpartImage(file, object, id);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData(path);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response.setSuccess(false);
            response.setMessage(ioe.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
