package ecommerce.system.api.controllers;

import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.SimpleMailModel;
import ecommerce.system.api.tools.EmailSender;
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

    @Autowired
    public TestController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkApplication() {

        logger.info("Checking if API is on...");

        BaseResponseModel<String> response = new BaseResponseModel<>(true, "Hello world!", "API is on.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/mail")
    public ResponseEntity<?> simpleMailTest(@RequestBody SimpleMailModel mail) {

        logger.info("Testing mail...");

        try {
            this.emailSender.sendSimpleMail(mail);

            logger.info("Mail tested with success");

            BaseResponseModel<SimpleMailModel> response = new BaseResponseModel<>(true, "E-mail enviado com sucesso!", mail);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            BaseResponseModel<String> response = new BaseResponseModel<>(false, "Um erro ocorreu.", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
