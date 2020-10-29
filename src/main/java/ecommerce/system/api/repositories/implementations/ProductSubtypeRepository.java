package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.ProductSubtypeEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.models.ProductSubtypeModel;
import ecommerce.system.api.repositories.IProductSubtypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class ProductSubtypeRepository implements IProductSubtypeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(ProductSubtypeModel object) {

        ProductSubtypeEntity productSubtype = new ProductSubtypeEntity(object);

        this.entityManager.persist(productSubtype);
    }

    @Override
    public List<ProductSubtypeModel> getAll() {

        String query = "FROM ProductSubtypeEntity p ORDER BY p.productTypeId ASC";
        TypedQuery<ProductSubtypeEntity> result = this.entityManager.createQuery(query, ProductSubtypeEntity.class);
        List<ProductSubtypeEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductSubtypeModel> productSubtypes = new ArrayList<>();
        (entities).forEach(productSubtype -> productSubtypes.add(productSubtype.toModel()));

        return productSubtypes;
    }

    @Override
    public ProductSubtypeModel getById(int id) {

        ProductSubtypeEntity productSubtype = this.entityManager.find(ProductSubtypeEntity.class, id);

        return productSubtype == null ? null : productSubtype.toModel();
    }

    @Override
    public void update(ProductSubtypeModel object) {

        ProductSubtypeEntity updatedProductSubtype = new ProductSubtypeEntity(object);

        this.entityManager.merge(updatedProductSubtype);
    }

    @Override
    public void delete(List<Integer> ids) throws BatchUpdateException {

        for (int id : ids) {
            ProductSubtypeEntity productSubtype = this.entityManager.find(ProductSubtypeEntity.class, id);

            if (productSubtype == null) {
                throw new BatchUpdateException("Erro ao deletar subtipo de produto. Nenhum subtipo de produto deletado!");
            }

            this.entityManager.remove(productSubtype);
        }
    }
}
