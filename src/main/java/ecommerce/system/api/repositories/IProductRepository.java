package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.util.List;

public interface IProductRepository {

    int createProduct(Product object);

    void createProductDetail(ProductDetail detail, int productId);

    int createProductImage(int productId, String path);

    List<Product> getProductsByQuantity(int quantity);

    List<Product> getProductsByNameAndQuantity(String name, int quantity);

    List<Product> getProductsByStoreIdAndQuantity(int storeId, int quantity);

    List<Product> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity);

    Product getProductById(int id);

    List<ProductDetail> getProductDetailLabelsByProductSubtypeId(int productSubtypeId);

    boolean updateProduct(Product object);

    boolean updateProductDetails(ProductDetail detail, int productId);

    boolean updateProductImage(ProductImage image);

    boolean deleteProduct(int id);
}
