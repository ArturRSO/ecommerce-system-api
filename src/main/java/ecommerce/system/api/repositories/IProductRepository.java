package ecommerce.system.api.repositories;

import ecommerce.system.api.models.DetailLabelModel;
import ecommerce.system.api.models.ProductDetailModel;
import ecommerce.system.api.models.ProductModel;

import java.util.List;

public interface IProductRepository extends IBaseRepository<ProductModel> {

    List<ProductModel> getProductsByStoreId(int storeId);
    List<ProductDetailModel> getProductDetailsByProductId(int productId);
    DetailLabelModel getLabelById(int labelId);
}
