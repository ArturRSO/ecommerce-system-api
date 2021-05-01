package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.models.ProductSubtypeModel;
import ecommerce.system.api.models.ProductTypeModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    void createProduct(ProductModel product);
    void createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException;
    List<ProductModel> getProductsByQuantity(int quantity) throws IOException;
    List<ProductModel> getProductsByNameAndQuantity(String name, int quantity) throws IOException;
    List<ProductModel> getProductsByStoreIdAndQuantity(int storeId, int quantity) throws IOException;
    List<ProductModel> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity) throws IOException;
    ProductModel getProductById(int productId) throws IOException;
    List<ProductTypeModel> getAllProductTypes();
    List<ProductSubtypeModel> getProductSubtypesByProductTypeId(int productTypeId);
    void updateProduct(ProductModel product) throws InvalidOperationException;
    void deleteProduct(int productId) throws InvalidOperationException;
}
