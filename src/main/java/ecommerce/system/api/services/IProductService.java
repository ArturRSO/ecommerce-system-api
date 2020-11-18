package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    void createProduct(ProductModel product);
    void createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException;
    List<ProductModel> getAllProducts();
    List<ProductModel> getProductsByStoreId(int storeId);
    ProductModel getProductById(int productId);
    String getProductImage(String path) throws IOException;
    void updateProduct(ProductModel product) throws InvalidOperationException;
    void deleteProducts(List<Integer> ids) throws InvalidOperationException;
}
