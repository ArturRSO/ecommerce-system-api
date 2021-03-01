package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.models.ProductSubtypeModel;
import ecommerce.system.api.models.ProductTypeModel;
import ecommerce.system.api.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.productService.createProduct(product);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

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

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

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

    @GetMapping("store/{storeId}")
    public ResponseEntity<?> getProductsByStoreId(@PathVariable("storeId") int storeId) {

        BaseResponseDTO<?> response;

        try {

            List<ProductModel> products = this.productService.getProductsByStoreId(storeId);

            if (products == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), products);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("sell")
    public ResponseEntity<?> getProductsToSell() {

        BaseResponseDTO<?> response;

        try {

            List<ProductModel> products = this.productService.getProductsToSell();

            if (products == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), products);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {

        BaseResponseDTO<?> response;

        try {

            ProductModel product = this.productService.getProductById(id);

            if (product == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), product);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("image")
    public ResponseEntity<?> getProductImage(@RequestParam String path) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            String imageBase64 = this.productService.getProductImage(path);

            response.setSuccess(true);
            response.setMessage(MessagesEnum.SUCCESS.getMessage());
            response.setData(imageBase64);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response.setSuccess(false);
            response.setMessage(MessagesEnum.FAILURE.getMessage());
            response.setData("");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("type/all")
    public ResponseEntity<?> getAllProductTypes() {

        BaseResponseDTO<?> response;

        try {

            List<ProductTypeModel> productTypes = this.productService.getAllProductTypes();

            if (productTypes == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), productTypes);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("type/{productTypeId}/subtypes/all")
    public ResponseEntity<?> getProductSubtypesByProductTypeId(@PathVariable("productTypeId") int productTypeId) {

        BaseResponseDTO<?> response;

        try {

            List<ProductSubtypeModel> productSubtypes = this.productService.getProductSubtypesByProductTypeId(productTypeId);

            if (productSubtypes == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), productSubtypes);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel product) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

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

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<?> deleteProducts(@PathVariable("productId") int productId) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.productService.deleteProduct(productId);

            response.setSuccess(true);
            response.setMessage("Produto deletado com sucesso!");
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
}
