package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;

import java.util.List;

public interface IProductService {

    void createProduct(ProductModel product);
    List<ProductModel> getAllProducts();
    ProductModel getProductById(int productId);
    void updateProduct(ProductModel product) throws InvalidOperationException;
    void deleteProducts(List<Integer> ids) throws BatchUpdateException;
}
