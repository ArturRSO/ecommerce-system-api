package ecommerce.system.api.repositories;

import ecommerce.system.api.models.ProductType;

import java.util.List;

public interface IProductTypeRepository {

    List<ProductType> getAll();

    ProductType getById(int id);
}
