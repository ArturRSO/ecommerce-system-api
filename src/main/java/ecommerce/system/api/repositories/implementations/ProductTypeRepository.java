package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.ProductType;
import ecommerce.system.api.repositories.IProductTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class ProductTypeRepository implements IProductTypeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductType> getAll() {

        String query = "FROM ProductType p ORDER BY p.productTypeId ASC";
        TypedQuery<ProductType> result = this.entityManager.createQuery(query, ProductType.class);
        List<ProductType> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public ProductType getById(int id) {

        ProductType productType = this.entityManager.find(ProductType.class, id);

        return productType == null ? null : productType;
    }
}
