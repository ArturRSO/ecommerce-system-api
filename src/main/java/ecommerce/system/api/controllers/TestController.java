package ecommerce.system.api.controllers;

import ecommerce.system.api.models.BaseResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/check")
    public ResponseEntity<?> checkApplication() {

        logger.info("Checking if api is on...");

        BaseResponseModel<String> response = new BaseResponseModel<>(true, "Hello world!", "API is on.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
