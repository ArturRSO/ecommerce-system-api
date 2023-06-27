package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.ProductSubtype;
import ecommerce.system.api.repositories.IProductSubtypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class ProductSubtypeRepository implements IProductSubtypeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductSubtype> getByProductTypeId(int productTypeId) {

        String query = "FROM ProductSubtype p WHERE p.productTypeId = :productTypeId ORDER BY p.productTypeId ASC";
        TypedQuery<ProductSubtype> result = this.entityManager.createQuery(query, ProductSubtype.class)
                .setParameter("productTypeId", productTypeId);
        List<ProductSubtype> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }

    @Override
    public ProductSubtype getById(int id) {

        ProductSubtype productSubtype = this.entityManager.find(ProductSubtype.class, id);

        return productSubtype == null ? null : productSubtype;
    }
}
