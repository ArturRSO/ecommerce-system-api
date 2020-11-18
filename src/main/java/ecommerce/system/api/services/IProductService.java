package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    void createProduct(ProductModel product);
    void createProductImage(MultipartFile file, int productId, int userId) throws InvalidOperationException, IOException;
    List<ProductModel> getAllProducts();
    List<ProductModel> getProductsByStoreId(int storeId, int userId) throws InvalidOperationException;
    ProductModel getProductById(int productId);
    String getProductImage(int productId, int userId, String path) throws InvalidOperationException, IOException;
    void updateProduct(ProductModel product, int userId) throws InvalidOperationException;
    void deleteProducts(List<Integer> ids, int userId) throws InvalidOperationException;
}
