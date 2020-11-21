package ecommerce.system.api.repositories;

import ecommerce.system.api.models.ProductSubtypeModel;

import java.util.List;

public interface IProductSubtypeRepository {

    List<ProductSubtypeModel> getByProductTypeId(int productTypeId);
    ProductSubtypeModel getById(int id);
}
