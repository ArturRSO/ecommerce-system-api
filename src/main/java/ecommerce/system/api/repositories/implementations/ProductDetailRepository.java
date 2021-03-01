package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.dto.ProductDetailLabelDTO;
import ecommerce.system.api.entities.ProductDetailEntity;
import ecommerce.system.api.entities.ProductDetailLabelEntity;
import ecommerce.system.api.models.ProductDetailModel;
import ecommerce.system.api.repositories.IProductDetailRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = {Exception.class})
public class ProductDetailRepository implements IProductDetailRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductDetailModel> getDetailsByProductId(int productId) {

        String query = "FROM ProductDetailEntity pd WHERE pd.id.productId = :productId";
        TypedQuery<ProductDetailEntity> result = this.entityManager.createQuery(query, ProductDetailEntity.class)
                .setParameter("productId", productId);
        List<ProductDetailEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductDetailModel> details = new ArrayList<>();

        entities.forEach(entity -> {
            ProductDetailLabelEntity label = this.getDetailLabelById(entity.getId().getDetailLabelId());
            ProductDetailModel detail = new ProductDetailModel(label.getName(), entity.getValue());
            details.add(detail);
        });

        return details;
    }

    @Override
    public List<ProductDetailLabelDTO> getDetailLabelsByProductSubtypeId(int productSubtypeId) {

        String query = "SELECT dl FROM ProductDetailLabelEntity dl, DetailLabelProductSubtypeEntity dlps WHERE dl.detailLabelId = dlps.id.detailLabelId AND dlps.id.productSubtypeId = :productSubtypeId";
        TypedQuery<ProductDetailLabelEntity> result = this.entityManager.createQuery(query, ProductDetailLabelEntity.class)
                .setParameter("productSubtypeId", productSubtypeId);
        List<ProductDetailLabelEntity> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductDetailLabelDTO> labels = new ArrayList<>();

        entities.forEach(entity -> {
            ProductDetailLabelDTO label = new ProductDetailLabelDTO(entity.getDetailLabelId(), entity.getName());
            labels.add(label);
        });

        return labels;
    }

    private ProductDetailLabelEntity getDetailLabelById(int id) {

        return this.entityManager.find(ProductDetailLabelEntity.class, id);
    }
}
