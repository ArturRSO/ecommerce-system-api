package ecommerce.system.api.repositories.implementations;

import ecommerce.system.api.dto.ProductDetailLabelDTO;
import ecommerce.system.api.models.ProductDetail;
import ecommerce.system.api.models.ProductDetailLabel;
import ecommerce.system.api.repositories.IProductDetailRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackOn = { Exception.class })
public class ProductDetailRepository implements IProductDetailRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductDetail> getDetailsByProductId(int productId) {

        String query = "FROM ProductDetail pd WHERE pd.id.productId = :productId";
        TypedQuery<ProductDetail> result = this.entityManager.createQuery(query, ProductDetail.class)
                .setParameter("productId", productId);
        List<ProductDetail> entities = result.getResultList();

        if (entities == null || entities.isEmpty()) {
            return null;
        }

        ArrayList<ProductDetail> details = new ArrayList<>();

        entities.forEach(entity -> {
            ProductDetailLabel label = this.getDetailLabelById(entity.getId().getDetailLabelId());
            ProductDetail detail = new ProductDetail(label.getDetailLabelId(), label.getName(), entity.getValue());
            details.add(detail);
        });

        return details;
    }

    @Override
    public List<ProductDetailLabelDTO> getDetailLabelsByProductSubtypeId(int productSubtypeId) {

        String query = "SELECT dl FROM ProductDetailLabel dl, DetailLabelProductSubtype dlps WHERE dl.detailLabelId = dlps.id.detailLabelId AND dlps.id.productSubtypeId = :productSubtypeId";
        TypedQuery<ProductDetailLabel> result = this.entityManager
                .createQuery(query, ProductDetailLabel.class)
                .setParameter("productSubtypeId", productSubtypeId);
        List<ProductDetailLabel> entities = result.getResultList();

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

    private ProductDetailLabel getDetailLabelById(int id) {

        return this.entityManager.find(ProductDetailLabel.class, id);
    }
}
