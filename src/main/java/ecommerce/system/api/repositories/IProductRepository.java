package ecommerce.system.api.repositories;

import ecommerce.system.api.models.*;

import java.util.List;

public interface IProductRepository {

    int createProduct(ProductModel object);
    void createProductDetail(ProductDetailModel detail, int productId);
    int createProductImage(int productId, String path);
    List<ProductModel> getProductsByQuantity(int quantity);
    List<ProductModel> getProductsByNameAndQuantity(String name, int quantity);
    List<ProductModel> getProductsByStoreIdAndQuantity(int storeId, int quantity);
    List<ProductModel> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity);
    ProductModel getProductById(int id);
    List<ProductDetailModel> getProductDetailLabelsByProductSubtypeId(int productSubtypeId);
    boolean updateProduct(ProductModel object);
    boolean updateProductDetails(ProductDetailModel detail, int productId);
    boolean updateProductImage(ProductImageModel image);
    boolean deleteProduct(int id);
}
