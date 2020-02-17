package ecommerce.system.api.controllers;

import ecommerce.system.api.models.BaseResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/check")
    public ResponseEntity<?> checkApplication() {

        BaseResponseModel<String> response = new BaseResponseModel<>(true, "Hello world!", "API is on.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
