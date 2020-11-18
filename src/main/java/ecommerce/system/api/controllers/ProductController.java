package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.BaseResponseModel;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.services.IProductService;
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
@RequestMapping("products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createProduct(@RequestBody ProductModel product) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.productService.createProduct(product);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

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
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("create/image/{productId}")
    public ResponseEntity<?> createProfileImage(@PathVariable("productId") int productId, @RequestParam("file") MultipartFile file) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.productService.createProductImage(file, productId);

            response.setSuccess(true);
            response.setMessage("Imagem cadastrada com sucesso!");
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }  catch (InvalidOperationException ioe) {

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
    public ResponseEntity<?> getAllProducts() {

        BaseResponseModel<?> response;

        try {

            List<ProductModel> products = this.productService.getAllProducts();

            if (products == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), products);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("store/{storeId}")
    public ResponseEntity<?> getProductsByStoreId(@PathVariable("storeId") int storeId) {

        BaseResponseModel<?> response;

        try {

            List<ProductModel> products = this.productService.getProductsByStoreId(storeId);

            if (products == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), products);
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
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {

        BaseResponseModel<?> response;

        try {

            ProductModel product = this.productService.getProductById(id);

            if (product == null) {
                response = new BaseResponseModel<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseModel<>(true, MessagesEnum.SUCCESS.getMessage(), product);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseModel<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("image/{productId}")
    public ResponseEntity<?> getProductImage(@PathVariable("productId") int productId, @RequestParam String path) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            String imageBase64 = this.productService.getProductImage(productId, path);

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

    @PutMapping("update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel product) {

        BaseResponseModel<String> response = new BaseResponseModel<>();

        try {

            this.productService.updateProduct(product);

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

    @DeleteMapping("delete}")
    public ResponseEntity<?> deleteProducts(@RequestBody ArrayList<Integer> ids) {

        // TODO
        return null;
    }
}
