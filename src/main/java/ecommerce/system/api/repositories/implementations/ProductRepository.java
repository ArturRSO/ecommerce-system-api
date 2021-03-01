package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.ProductEntity;
import ecommerce.system.api.models.*;
import ecommerce.system.api.repositories.IProductRepository;
import ecommerce.system.api.repositories.IProductSubtypeRepository;
import ecommerce.system.api.repositories.IProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class ProductRepository implements IProductRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProductSubtypeRepository productSubtypeRepository;
    private final IProductTypeRepository productTypeRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ProductRepository(IProductSubtypeRepository productSubtypeRepository, IProductTypeRepository productTypeRepository) {
        this.productSubtypeRepository = productSubtypeRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public int create(ProductModel object) {

        ProductEntity product = new ProductEntity(object);

        this.entityManager.persist(product);
        this.entityManager.flush();

        return product.getProductId();
    }

    @Override
    public List<ProductModel> getProductsByStoreId(int storeId) {

        String query = "FROM ProductEntity p WHERE p.storeId = :storeId AND p.active = true ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                .setParameter("storeId", storeId);
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<ProductModel> getProductsToSell() {

        String query = "FROM ProductEntity p WHERE p.active = true AND p.quantity > 0 ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class);
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public ProductModel getById(int id) {

        try {

            String query = "FROM ProductEntity p WHERE p.active = true AND p.productId = :productId";
            TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("productId", id);
            ProductEntity entity = result.getSingleResult();

            ProductModel product = entity.toModel();
            ProductTypeModel productType = this.productTypeRepository.getById(entity.getProductTypeId());
            ProductSubtypeModel productSubtype = this.productSubtypeRepository.getById(entity.getProductSubtypeId());

            product.setProductType(productType);
            product.setProductSubtype(productSubtype);

            return product;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public boolean update(ProductModel object) {

        ProductEntity product = this.entityManager.find(ProductEntity.class, object.getProductId());

        if (product == null || !product.isActive()) {
            return false;
        }

        ProductEntity updatedProduct = new ProductEntity(object);

        this.entityManager.merge(updatedProduct);

        return true;
    }

    @Override
    public boolean delete(int id) {

        ProductEntity product = this.entityManager.find(ProductEntity.class, id);

        if (product == null || !product.isActive()) {
            return false;
        }

        product.setActive(false);
        product.setLastUpdate(LocalDateTime.now());

        return true;
    }

    private List<ProductModel> buildProducts(List<ProductEntity> entities) {

        ArrayList<ProductModel> products = new ArrayList<>();

        (entities).forEach(entity -> {
            ProductTypeModel productType = this.productTypeRepository.getById(entity.getProductTypeId());
            ProductSubtypeModel productSubtype = this.productSubtypeRepository.getById(entity.getProductSubtypeId());
            ProductModel product = entity.toModel();
            product.setProductType(productType);
            product.setProductSubtype(productSubtype);
            products.add(product);
        });

        return products;
    }
}
