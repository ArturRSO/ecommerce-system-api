package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.StoreModel;
import ecommerce.system.api.services.IStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("stores")
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IStoreService storeService;

    @Autowired
    public StoreController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("create/{userId}")
    public ResponseEntity<?> createStore(@RequestBody StoreModel store, @PathVariable("userId") int userId) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.storeService.createStore(store, userId);

            response.setSuccess(true);
            response.setMessage("Loja criada com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

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

    @PostMapping("create/image/{storeId}/{userId}")
    public ResponseEntity<?> createProfileImage(@PathVariable("storeId") int storeId, @PathVariable("userId") int userId, @RequestParam("file") MultipartFile file) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.storeService.createProfileImage(file, storeId, userId);

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

        }  catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllStores() {

        BaseResponseModel<?> response;

        try {

            List<StoreModel> stores = this.storeService.getAllStores();

            if (stores == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), stores);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getStoresByUserId(@PathVariable("userId") int userId) {

        BaseResponseModel<?> response;

        try {

            List<StoreModel> stores = this.storeService.getStoresByUserId(userId);

            if (stores == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), stores);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (InvalidOperationException ioe) {

            logger.error(ioe.getMessage());

            response = new BaseResponseModel<>(false, ioe.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStoreById(@PathVariable("id") int id) {

        BaseResponseModel<?> response;

        try {

            StoreModel store = this.storeService.getStoreById(id);

            if (store == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), store);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("image/{storeId}/{userId}")
    public ResponseEntity<?> getProfileImage(@PathVariable("storeId") int storeId, @PathVariable("userId") int userId, @RequestParam String path) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            String imageBase64 = this.storeService.getProfileImage(storeId, userId, path);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData(imageBase64);

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

    @PutMapping("update/user/{userId}")
    public ResponseEntity<?> updateStore(@RequestBody StoreModel store, @PathVariable("userId") int userId) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.storeService.updateStore(store, userId);

            response.setSuccess(true);
            response.setMessage("Loja atualizada com sucesso!");
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

    @DeleteMapping("delete/user/{userId}")
    public ResponseEntity<?> deleteStores(@PathVariable("userId") int userId, @RequestBody ArrayList<Integer> ids) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.storeService.deleteStores(ids, userId);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

            // TODO
//        }  catch (InvalidOperationException ioe) {
//
//            logger.error(ioe.getMessage());
//
//            response.setSuccess(false);
//            response.setMessage(ioe.getMessage());
//            response.setData("");
//
//            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
