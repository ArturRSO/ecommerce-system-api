package ecommerce.system.api.repositories;

import ecommerce.system.api.models.ProductTypeModel;

import java.util.List;

public interface IProductTypeRepository {

    List<ProductTypeModel> getAll();
    ProductTypeModel getById(int id);
}
