package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.SimpleMailModel;
import ecommerce.system.api.models.UserModel;
import ecommerce.system.api.tools.EmailSender;
import ecommerce.system.api.tools.PasswordRecoverHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmailSender emailSender;
    private final PasswordRecoverHandler passwordRecoverHandler;

    @Autowired
    public TestController(EmailSender emailSender, PasswordRecoverHandler passwordRecoverHandler) {
        this.emailSender = emailSender;
        this.passwordRecoverHandler = passwordRecoverHandler;
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkApplication() {

        logger.info("Checking if API is on...");

        BaseResponseModel<String> response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), "Hello world!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/mail")
    public ResponseEntity<?> simpleMailTest(@RequestBody SimpleMailModel mail) {

        logger.info("Testing mail...");

        try {
            this.emailSender.sendSimpleMail(mail);

            logger.info("Mail tested with success!");

            BaseResponseModel<SimpleMailModel> response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), mail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("mail/recover/password")
    public ResponseEntity<?> sendRecoverPasswordEmail(@RequestBody UserModel user) {

        logger.info("Testing recover password mail...");

        try {
            SimpleMailModel mail = this.passwordRecoverHandler.sendEmail(user.getUserId(), user.getEmail());

            logger.info("Recover password mail tested with success!");

            BaseResponseModel<SimpleMailModel> response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), mail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
