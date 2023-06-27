package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductDetail;
import ecommerce.system.api.models.Product;
import ecommerce.system.api.models.ProductSubtype;
import ecommerce.system.api.models.ProductType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    int createProduct(Product product);

    int createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException;

    List<Product> getProductsByQuantity(int quantity) throws IOException;

    List<Product> getProductsByNameAndQuantity(String name, int quantity) throws IOException;

    List<Product> getProductsByStoreIdAndQuantity(int storeId, int quantity) throws IOException;

    List<Product> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity) throws IOException;

    Product getProductById(int productId) throws IOException;

    List<ProductType> getAllProductTypes();

    List<ProductSubtype> getProductSubtypesByProductTypeId(int productTypeId);

    List<ProductDetail> getProductDetailLabelsByProductSubtypeId(int productSubtypeId);

    void updateProduct(Product product, boolean systemUpdate) throws InvalidOperationException, IOException;

    void updateProductImage(MultipartFile file, int productId, int imageId)
            throws InvalidOperationException, IOException;

    void deleteProduct(int productId) throws InvalidOperationException, IOException;
}
