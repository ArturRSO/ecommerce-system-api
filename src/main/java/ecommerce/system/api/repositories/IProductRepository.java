package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.util.List;

public interface IProductRepository extends ICrudRepository<ProductModel> {

    void createProductImage(int productId, String path);
    List<ProductModel> getProductsByStoreId(int storeId);
    List<ProductModel> getProductsBySubtypeId(int subtypeId);
    List<ProductModel> getProductsToSell();
}
