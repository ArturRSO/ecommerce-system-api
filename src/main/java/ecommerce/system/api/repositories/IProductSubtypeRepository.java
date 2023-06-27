package ecommerce.system.api.repositories;

import ecommerce.system.api.models.ProductSubtype;

import java.util.List;

public interface IProductSubtypeRepository {

    List<ProductSubtype> getByProductTypeId(int productTypeId);

    ProductSubtype getById(int id);
}
