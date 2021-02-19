package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.util.List;

public interface IProductRepository extends ICrudRepository<ProductModel> {

    List<ProductModel> getProductsByStoreId(int storeId);
    List<ProductModel> getProductsToSell();
}
