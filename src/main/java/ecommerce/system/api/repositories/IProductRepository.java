package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.util.List;

public interface IProductRepository {

    int createProduct(ProductModel object);
    void createProductImage(int productId, String path);
    List<ProductModel> getProductsByQuantity(int quantity);
    List<ProductModel> getProductsByNameAndQuantity(String name, int quantity);
    List<ProductModel> getProductsByStoreIdAndQuantity(int storeId, int quantity);
    List<ProductModel> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity);
    ProductModel getProductById(int id);
    boolean updateProduct(ProductModel object);
    boolean deleteProduct(int id);
}
