package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.DetailLabelEntity;
import ecommerce.system.api.entities.ProductDetailEntity;
import ecommerce.system.api.entities.ProductEntity;
import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.models.DetailLabelModel;
import ecommerce.system.api.models.ProductDetailModel;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.repositories.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class ProductRepository implements IProductRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(ProductModel object) {
        ProductEntity product = new ProductEntity(object);

        this.entityManager.persist(product);
    }

    @Override
    public List<ProductModel> getAll() {

        try {

            String query = "FROM ProductEntity p WHERE p.active = true ORDER BY p.productId ASC";
            TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class);
            List<ProductEntity> entities = result.getResultList();

            if (entities == null || entities.isEmpty()) {
                return null;
            }

            ArrayList<ProductModel> products = new ArrayList<>();

            (entities).forEach(entity -> {
                List<ProductDetailModel> details = this.getProductDetailsByProductId(entity.getProductId());
                ProductModel product = entity.toModel();
                product.setDetails(details);
            });

            return products;

        } catch (Exception e) {

            logger.error(e.getMessage());

            return null;
        }
    }

    @Override
    public ProductModel getById(int id) {
        return null;
    }

    @Override
    public void update(ProductModel object) {

    }

    @Override
    public void delete(List<Integer> ids) throws BatchUpdateException {

    }

    @Override
    public List<ProductDetailModel> getProductDetailsByProductId(int productId) {

         try {

             String query = "FROM ProductDetailEntity p WHERE p.productId = :productId";
             TypedQuery<ProductDetailEntity> result = this.entityManager.createQuery(query, ProductDetailEntity.class);
             List<ProductDetailEntity> entities = result.getResultList();

             if (entities == null || entities.isEmpty()) {
                 return null;
             }

             ArrayList<ProductDetailModel> details = new ArrayList<>();

             (entities).forEach((entity) -> {
                 DetailLabelModel label = this.getLabelById(entity.getDetailLabelId());
                 ProductDetailModel detail = entity.toModel();
                 detail.setLabel(label);
                 details.add(detail);
             });

             return details;

         } catch (Exception e) {

             logger.error(e.getMessage());

             return null;
         }
    }

    @Override
    public DetailLabelModel getLabelById(int labelId) {

        DetailLabelEntity label = this.entityManager.find(DetailLabelEntity.class, labelId);

        return label == null ? null : label.toModel();
    }
}
