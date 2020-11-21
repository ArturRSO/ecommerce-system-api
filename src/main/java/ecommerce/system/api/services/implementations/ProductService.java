package ecommerce.system.api.services.implementations;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.ProductModel;
import ecommerce.system.api.models.ProductSubtypeModel;
import ecommerce.system.api.models.ProductTypeModel;
import ecommerce.system.api.repositories.IProductRepository;
import ecommerce.system.api.repositories.IProductSubtypeRepository;
import ecommerce.system.api.repositories.IProductTypeRepository;
import ecommerce.system.api.services.IFileService;
import ecommerce.system.api.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IFileService fileService;
    private final IProductRepository productRepository;
    private final IProductTypeRepository productTypeRepository;
    private final IProductSubtypeRepository productSubtypeRepository;

    @Autowired
    public ProductService(IFileService fileService, IProductRepository productRepository, IProductTypeRepository productTypeRepository, IProductSubtypeRepository productSubtypeRepository) {
        this.fileService = fileService;
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.productSubtypeRepository = productSubtypeRepository;
    }

    @Override
    public void createProduct(ProductModel product) {

        product.setImagePath(null);
        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdate(null);
        product.setActive(true);

        this.productRepository.create(product);
    }

    @Override
    public void createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException {

        String imagePath = this.fileService.saveMultpartImage(file, "product", productId);

        ProductModel product = this.productRepository.getById(productId);
        product.setImagePath(imagePath);
        product.setLastUpdate(LocalDateTime.now());

        if (!this.productRepository.update(product)) {
            throw new InvalidOperationException("Produto não encontrado!");
        }
    }

    @Override
    public List<ProductModel> getAllProducts() {

        return this.productRepository.getAll();
    }

    @Override
    public List<ProductModel> getProductsByStoreId(int storeId) {

        return this.productRepository.getProductsByStoreId(storeId);
    }

    @Override
    public List<ProductModel> getProductsToSell() {

        return this.productRepository.getProductsToSell();
    }

    @Override
    public ProductModel getProductById(int productId) {

        return this.productRepository.getById(productId);
    }

    @Override
    public String getProductImage(String path) throws IOException {

        return this.fileService.getImageBase64(UriUtils.decode(path, "UTF-8"));
    }

    @Override
    public List<ProductTypeModel> getAllProductTypes() {

        return this.productTypeRepository.getAll();
    }

    @Override
    public List<ProductSubtypeModel> getProductSubtypesByProductTypeId(int productTypeId) {

        return this.productSubtypeRepository.getByProductTypeId(productTypeId);
    }

    @Override
    public void updateProduct(ProductModel product) throws InvalidOperationException {

        ProductModel oldProduct = this.productRepository.getById(product.getProductId());

        product.setCreationDate(oldProduct.getCreationDate());
        product.setLastUpdate(LocalDateTime.now());
        product.setActive(oldProduct.isActive());

        if (!this.productRepository.update(product)) {
            throw new InvalidOperationException("Produto não encontrado!");
        }
    }

    @Override
    public void deleteProducts(List<Integer> ids) throws InvalidOperationException {

        int deletionCount = 0;

        for (int id : ids) {

            if (this.productRepository.delete(id)) {
                deletionCount++;
            }
        }

        if (ids.size() != deletionCount) {

            int deletionFails = ids.size() - deletionCount;

            throw new InvalidOperationException("Erro: " + deletionCount + " produto(s) deletado(s), " + deletionFails + " produto(s) não encontrado(s).");
        }
    }
}
