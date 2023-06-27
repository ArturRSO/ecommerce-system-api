package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.models.*;
import ecommerce.system.api.models.embedded.ProductDetailKey;
import ecommerce.system.api.repositories.IProductDetailRepository;
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
@Transactional(rollbackOn = { Exception.class })
public class ProductRepository implements IProductRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProductDetailRepository detailRepository;
    private final IProductSubtypeRepository productSubtypeRepository;
    private final IProductTypeRepository productTypeRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ProductRepository(IProductDetailRepository detailRepository,
            IProductSubtypeRepository productSubtypeRepository, IProductTypeRepository productTypeRepository) {
        this.detailRepository = detailRepository;
        this.productSubtypeRepository = productSubtypeRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public int createProduct(Product object) {

        this.entityManager.persist(object);
        this.entityManager.flush();

        return object.getProductId();
    }

    @Override
    public void createProductDetail(ProductDetail detail, int productId) {

        ProductDetail entity = new ProductDetail(detail, productId);

        this.entityManager.persist(entity);
    }

    @Override
    public int createProductImage(int productId, String path) {

        ProductImage productImage = new ProductImage(productId, path);

        this.entityManager.persist(productImage);
        this.entityManager.flush();

        return productImage.getProductImageId();
    }

    @Override
    public List<Product> getProductsByQuantity(int quantity) {

        String query = "FROM Product p WHERE p.active = true AND p.quantity >= :quantity ORDER BY p.productId ASC";
        TypedQuery<Product> result = this.entityManager.createQuery(query, Product.class)
                .setParameter("quantity", quantity);
        List<Product> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<Product> getProductsByNameAndQuantity(String name, int quantity) {

        String query = "FROM Product p WHERE p.active = true AND p.quantity >= :quantity AND p.name LIKE :name ORDER BY p.productId ASC";
        TypedQuery<Product> result = this.entityManager.createQuery(query, Product.class)
                .setParameter("quantity", quantity)
                .setParameter("name", "%" + name + "%");
        List<Product> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<Product> getProductsByStoreIdAndQuantity(int storeId, int quantity) {

        String query = "FROM Product p WHERE p.storeId = :storeId AND p.active = true AND p.quantity >= :quantity ORDER BY p.productId ASC";
        TypedQuery<Product> result = this.entityManager.createQuery(query, Product.class)
                .setParameter("storeId", storeId)
                .setParameter("quantity", quantity);
        List<Product> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<Product> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity) {

        String query = "FROM Product p WHERE p.active = true AND p.quantity >= :quantity AND p.productSubtypeId = :subtypeId ORDER BY p.productId ASC";
        TypedQuery<Product> result = this.entityManager.createQuery(query, Product.class)
                .setParameter("subtypeId", subtypeId)
                .setParameter("quantity", quantity);
        List<Product> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public Product getProductById(int id) {

        try {

            String query = "FROM Product p WHERE p.active = true AND p.productId = :productId";
            TypedQuery<Product> result = this.entityManager.createQuery(query, Product.class)
                    .setParameter("productId", id);
            Product entity = result.getSingleResult();

            ProductType productType = this.productTypeRepository.getById(entity.getProductTypeId());
            ProductSubtype productSubtype = this.productSubtypeRepository.getById(entity.getProductSubtypeId());
            List<ProductDetail> details = this.detailRepository.getDetailsByProductId(entity.getProductId());
            List<ProductImage> images = this.getImagesByProductId(entity.getProductId());

            entity.setDetails(details);
            entity.setImages(images);
            entity.setProductType(productType);
            entity.setProductSubtype(productSubtype);

            return entity;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<ProductDetail> getProductDetailLabelsByProductSubtypeId(int productSubtypeId) {

        String query = "SELECT pdl FROM ProductDetailLabel pdl, DetailLabelProductSubtypeEntity dlps WHERE dlps.id.detailLabelId = pdl.detailLabelId AND dlps.id.productSubtypeId = :productSubtypeId ORDER BY pdl.detailLabelId ASC";
        TypedQuery<ProductDetailLabel> result = this.entityManager
                .createQuery(query, ProductDetailLabel.class)
                .setParameter("productSubtypeId", productSubtypeId);
        List<ProductDetailLabel> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductDetail> detailLabels = new ArrayList<>();

        (entities).forEach((entity) -> detailLabels.add(entity.toProductDetail()));

        return detailLabels;
    }

    @Override
    public boolean updateProduct(Product object) {

        Product product = this.entityManager.find(Product.class, object.getProductId());

        if (product == null || !product.isActive()) {
            return false;
        }

        this.entityManager.merge(object);

        return true;
    }

    @Override
    public boolean updateProductDetails(ProductDetail detail, int productId) {

        ProductDetail entity = this.entityManager.find(ProductDetail.class,
                new ProductDetailKey(productId, detail.getLabelId()));

        if (entity == null) {
            return false;
        }

        ProductDetail updatedDetail = new ProductDetail(detail, productId);

        this.entityManager.merge(updatedDetail);

        return true;
    }

    @Override
    public boolean updateProductImage(ProductImage image) {

        ProductImage entity = this.entityManager.find(ProductImage.class, image.getProductImageId());

        if (entity == null) {
            return false;
        }

        this.entityManager.merge(image);

        return true;
    }

    @Override
    public boolean deleteProduct(int id) {

        Product product = this.entityManager.find(Product.class, id);

        if (product == null || !product.isActive()) {
            return false;
        }

        product.setActive(false);
        product.setLastUpdate(LocalDateTime.now());

        return true;
    }

    private List<Product> buildProducts(List<Product> entities) {

        (entities).forEach(entity -> {
            ProductType productType = this.productTypeRepository.getById(entity.getProductTypeId());
            ProductSubtype productSubtype = this.productSubtypeRepository.getById(entity.getProductSubtypeId());
            List<ProductDetail> details = this.detailRepository.getDetailsByProductId(entity.getProductId());
            List<ProductImage> images = this.getImagesByProductId(entity.getProductId());
            entity.setDetails(details);
            entity.setImages(images);
            entity.setProductType(productType);
            entity.setProductSubtype(productSubtype);

        });

        return entities;
    }

    private List<ProductImage> getImagesByProductId(int productId) {

        String query = "FROM ProductImage p WHERE p.productId = :productId ORDER BY p.productImageId ASC";
        TypedQuery<ProductImage> result = this.entityManager.createQuery(query, ProductImage.class)
                .setParameter("productId", productId);
        List<ProductImage> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities;
    }
}
