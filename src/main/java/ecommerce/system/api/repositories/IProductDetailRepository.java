package ecommerce.system.api.repositories;

import ecommerce.system.api.dto.ProductDetailLabelDTO;
import ecommerce.system.api.models.ProductDetailModel;

import java.util.List;

public interface IProductDetailRepository {

    List<ProductDetailModel> getDetailsByProductId(int productId);
    List<ProductDetailLabelDTO> getDetailLabelsByProductSubtypeId(int productSubtypeId);
}
