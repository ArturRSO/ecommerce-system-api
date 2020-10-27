package ecommerce.system.api.services.implementations;

import ecommerce.system.api.exceptions.BatchUpdateException;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.repositories.IProductRepository;
import ecommerce.system.api.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(ProductModel product) {

        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdate(null);
        product.setActive(true);

        this.productRepository.create(product);
    }

    @Override
    public List<ProductModel> getAllProducts() {

        return this.productRepository.getAll();
    }

    @Override
    public ProductModel getProductById(int productId) {

        return this.productRepository.getById(productId);
    }

    @Override
    public void updateProduct(ProductModel product) throws InvalidOperationException {

        ProductModel oldProduct = this.productRepository.getById(product.getProductId());

        if (oldProduct == null) {
            throw new InvalidOperationException("Produto não encontrado, não foi possível atualizar!");
        }

        product.setCreationDate(oldProduct.getCreationDate());
        product.setLastUpdate(LocalDateTime.now());
        product.setActive(oldProduct.isActive());

        this.productRepository.update(product);
    }

    @Override
    public void deleteProducts(List<Integer> ids) throws BatchUpdateException {

        this.productRepository.delete(ids);
    }
}
