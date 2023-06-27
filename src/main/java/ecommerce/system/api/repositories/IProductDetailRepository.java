package ecommerce.system.api.repositories;

import ecommerce.system.api.dto.ProductDetailLabelDTO;
import ecommerce.system.api.models.ProductDetail;

import java.util.List;

public interface IProductDetailRepository {

    List<ProductDetail> getDetailsByProductId(int productId);

    List<ProductDetailLabelDTO> getDetailLabelsByProductSubtypeId(int productSubtypeId);
}
