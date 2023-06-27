package ecommerce.system.api.controllers;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.dto.BaseResponseDTO;
import ecommerce.system.api.models.ProductDetail;
import ecommerce.system.api.models.Product;
import ecommerce.system.api.models.ProductSubtype;
import ecommerce.system.api.models.ProductType;
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
    public ResponseEntity<?> createProduct(@RequestBody Product product) {

        BaseResponseDTO<?> response;

        try {

            int productId = this.productService.createProduct(product);

            response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), productId);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.error(e.getMessage());

            response = new BaseResponseDTO<>(false, MessagesEnum.FAILURE.getMessage(), "");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("create/image/{productId}")
    public ResponseEntity<?> createProductImage(@PathVariable("productId") int productId,
            @RequestParam("file") MultipartFile file) {

        BaseResponseDTO<?> response;

        try {

            int productImageId = this.productService.createProductImage(file, productId);

            response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), productImageId);

            return new ResponseEntity<>(response, HttpStatus.OK);

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

    @GetMapping("all")
    public ResponseEntity<?> getProductsByQuantity(@RequestParam("quantity") int quantity) {

        BaseResponseDTO<?> response;

        try {

            List<Product> products = this.productService.getProductsByQuantity(quantity);

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

    @GetMapping("search")
    public ResponseEntity<?> getProductsByNameAndQuantity(@RequestParam("name") String name,
            @RequestParam("quantity") int quantity) {

        BaseResponseDTO<?> response;

        try {

            List<Product> products = this.productService.getProductsByNameAndQuantity(name, quantity);

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

    @GetMapping("store/{storeId}")
    public ResponseEntity<?> getProductsByStoreIdAndQuantity(@PathVariable("storeId") int storeId,
            @RequestParam("quantity") int quantity) {

        BaseResponseDTO<?> response;

        try {

            List<Product> products = this.productService.getProductsByStoreIdAndQuantity(storeId, quantity);

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

    @GetMapping("subtype/{subtypeId}")
    public ResponseEntity<?> getProductsBySubtypeIdAndQuantity(@PathVariable("subtypeId") int subtypeId,
            @RequestParam("quantity") int quantity) {

        BaseResponseDTO<?> response;

        try {

            List<Product> products = this.productService.getProductsBySubtypeIdAndQuantity(subtypeId, quantity);

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

    @GetMapping("details/labels/subtype/{subtypeId}")
    public ResponseEntity<?> getProductDetailLabelsByProductSubtypeId(@PathVariable("subtypeId") int productSubtypeId) {

        BaseResponseDTO<?> response;

        try {

            List<ProductDetail> detailLabels = this.productService
                    .getProductDetailLabelsByProductSubtypeId(productSubtypeId);

            if (detailLabels == null) {
                response = new BaseResponseDTO<>(false, MessagesEnum.NOT_FOUND.getMessage(), "");

            } else {
                response = new BaseResponseDTO<>(true, MessagesEnum.SUCCESS.getMessage(), detailLabels);
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

            Product product = this.productService.getProductById(id);

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

    @GetMapping("type/all")
    public ResponseEntity<?> getAllProductTypes() {

        BaseResponseDTO<?> response;

        try {

            List<ProductType> productTypes = this.productService.getAllProductTypes();

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

            List<ProductSubtype> productSubtypes = this.productService
                    .getProductSubtypesByProductTypeId(productTypeId);

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
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.productService.updateProduct(product, false);

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

    @PutMapping("update/image/{imageId}/product{productId}")
    public ResponseEntity<?> updateProductImage(@PathVariable("imageId") int imageId,
            @PathVariable("productId") int productId, @RequestParam("file") MultipartFile file) {

        BaseResponseDTO<String> response = new BaseResponseDTO<>();

        try {

            this.productService.updateProductImage(file, productId, imageId);

            response.setSuccess(true);
            response.setMessage("Imagem atualizada com sucesso!");
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
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId) {

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
