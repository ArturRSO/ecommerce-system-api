package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.entities.ProductDetailEntity;
import ecommerce.system.api.entities.ProductDetailLabelEntity;
import ecommerce.system.api.entities.ProductEntity;
import ecommerce.system.api.entities.ProductImageEntity;
import ecommerce.system.api.entities.embedded.ProductDetailKey;
import ecommerce.system.api.models.*;
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
@Transactional(rollbackOn = {Exception.class})
public class ProductRepository implements IProductRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IProductDetailRepository detailRepository;
    private final IProductSubtypeRepository productSubtypeRepository;
    private final IProductTypeRepository productTypeRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ProductRepository(IProductDetailRepository detailRepository, IProductSubtypeRepository productSubtypeRepository, IProductTypeRepository productTypeRepository) {
        this.detailRepository = detailRepository;
        this.productSubtypeRepository = productSubtypeRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public int createProduct(ProductModel object) {

        ProductEntity product = new ProductEntity(object);

        this.entityManager.persist(product);
        this.entityManager.flush();

        return product.getProductId();
    }

    @Override
    public void createProductDetail(ProductDetailModel detail, int productId) {

        ProductDetailEntity entity = new ProductDetailEntity(detail, productId);

        this.entityManager.persist(entity);
    }

    @Override
    public int createProductImage(int productId, String path) {

        ProductImageEntity productImage = new ProductImageEntity(productId, path);

        this.entityManager.persist(productImage);
        this.entityManager.flush();

        return productImage.getProductImageId();
    }

    @Override
    public List<ProductModel> getProductsByQuantity(int quantity) {

        String query = "FROM ProductEntity p WHERE p.active = true AND p.quantity >= :quantity ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                .setParameter("quantity", quantity);
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<ProductModel> getProductsByNameAndQuantity(String name, int quantity) {

        String query = "FROM ProductEntity p WHERE p.active = true AND p.quantity >= :quantity AND p.name LIKE :name ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                .setParameter("quantity", quantity)
                .setParameter("name", "%" + name + "%");
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<ProductModel> getProductsByStoreIdAndQuantity(int storeId, int quantity) {

        String query = "FROM ProductEntity p WHERE p.storeId = :storeId AND p.active = true AND p.quantity >= :quantity ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                .setParameter("storeId", storeId)
                .setParameter("quantity", quantity);
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public List<ProductModel> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity) {

        String query = "FROM ProductEntity p WHERE p.active = true AND p.quantity >= :quantity AND p.productSubtypeId = :subtypeId ORDER BY p.productId ASC";
        TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                .setParameter("subtypeId", subtypeId)
                .setParameter("quantity", quantity);
        List<ProductEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return this.buildProducts(entities);
    }

    @Override
    public ProductModel getProductById(int id) {

        try {

            String query = "FROM ProductEntity p WHERE p.active = true AND p.productId = :productId";
            TypedQuery<ProductEntity> result = this.entityManager.createQuery(query, ProductEntity.class)
                    .setParameter("productId", id);
            ProductEntity entity = result.getSingleResult();

            ProductModel product = entity.toModel();
            ProductTypeModel productType = this.productTypeRepository.getById(entity.getProductTypeId());
            ProductSubtypeModel productSubtype = this.productSubtypeRepository.getById(entity.getProductSubtypeId());
            List<ProductDetailModel> details = this.detailRepository.getDetailsByProductId(entity.getProductId());
            List<ProductImageModel> images = this.getImagesByProductId(entity.getProductId());

            product.setDetails(details);
            product.setImages(images);
            product.setProductType(productType);
            product.setProductSubtype(productSubtype);

            return product;

        } catch (NoResultException nre) {

            logger.error(nre.getMessage());

            return null;
        }
    }

    @Override
    public List<ProductDetailModel> getProductDetailLabelsByProductSubtypeId(int productSubtypeId) {

        String query = "SELECT pdl FROM ProductDetailLabelEntity pdl, DetailLabelProductSubtypeEntity dlps WHERE dlps.id.detailLabelId = pdl.detailLabelId AND dlps.id.productSubtypeId = :productSubtypeId ORDER BY pdl.detailLabelId ASC";
        TypedQuery<ProductDetailLabelEntity> result = this.entityManager.createQuery(query, ProductDetailLabelEntity.class)
                .setParameter("productSubtypeId", productSubtypeId);
        List<ProductDetailLabelEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductDetailModel> detailLabels = new ArrayList<>();

        (entities).forEach((entity) -> detailLabels.add(entity.toModel()));

        return detailLabels;
    }

    @Override
    public boolean updateProduct(ProductModel object) {

        ProductEntity product = this.entityManager.find(ProductEntity.class, object.getProductId());

        if (product == null || !product.isActive()) {
            return false;
        }

        ProductEntity updatedProduct = new ProductEntity(object);

        this.entityManager.merge(updatedProduct);

        return true;
    }

    @Override
    public boolean updateProductDetails(ProductDetailModel detail, int productId) {

        ProductDetailEntity entity = this.entityManager.find(ProductDetailEntity.class, new ProductDetailKey(productId, detail.getLabelId()));

        if (entity == null) {
            return false;
        }

        ProductDetailEntity updatedDetail = new ProductDetailEntity(detail, productId);

        this.entityManager.merge(updatedDetail);

        return true;
    }

    @Override
    public boolean updateProductImage(ProductImageModel image) {

        ProductImageEntity entity = this.entityManager.find(ProductImageEntity.class, image.getProductImageId());

        if (entity == null) {
            return false;
        }

        ProductImageEntity updatedImage = new ProductImageEntity(image);

        this.entityManager.merge(updatedImage);

        return true;
    }

    @Override
    public boolean deleteProduct(int id) {

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
            List<ProductDetailModel> details = this.detailRepository.getDetailsByProductId(entity.getProductId());
            List<ProductImageModel> images = this.getImagesByProductId(entity.getProductId());
            ProductModel product = entity.toModel();
            product.setDetails(details);
            product.setImages(images);
            product.setProductType(productType);
            product.setProductSubtype(productSubtype);
            products.add(product);
        });

        return products;
    }

    private List<ProductImageModel> getImagesByProductId(int productId) {

        String query = "FROM ProductImageEntity p WHERE p.productId = :productId ORDER BY p.productImageId ASC";
        TypedQuery<ProductImageEntity> result = this.entityManager.createQuery(query, ProductImageEntity.class)
                .setParameter("productId", productId);
        List<ProductImageEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductImageModel> paths = new ArrayList<>();

        entities.forEach(entity -> paths.add(entity.toModel()));

        return paths;
    }
}
