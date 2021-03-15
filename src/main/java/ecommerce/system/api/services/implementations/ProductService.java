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
import java.util.ArrayList;
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

        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdate(null);
        product.setActive(true);

        this.productRepository.create(product);
    }

    @Override
    public void createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException {

        String imagePath = this.fileService.saveMultpartImage(file, "product", productId);

        ProductModel product = this.productRepository.getById(productId);

        if (product == null) {
            throw new InvalidOperationException("Produto não encontrado!");
        }

        this.productRepository.createProductImage(productId, imagePath);
    }

    @Override
    public List<ProductModel> getProductsByStoreId(int storeId) throws IOException {

        List<ProductModel> products = this.productRepository.getProductsByStoreId(storeId);

        if (products != null) {
            for (ProductModel product : products) {
                List<String> images = this.getImagesByPaths(product.getImageList());
                product.setImageList(images);
            }
        }

        return products;
    }

    @Override
    public List<ProductModel> getProductsBySubtypeId(int subtypeId) throws IOException {

        List<ProductModel> products = this.productRepository.getProductsBySubtypeId(subtypeId);

        if (products != null) {
            for (ProductModel product : products) {
                List<String> images = this.getImagesByPaths(product.getImageList());
                product.setImageList(images);
            }
        }

        return products;
    }

    @Override
    public List<ProductModel> getProductsToSell() throws IOException {

        List<ProductModel> products = this.productRepository.getProductsToSell();

        if (products != null) {
            for (ProductModel product : products) {
                List<String> images = this.getImagesByPaths(product.getImageList());
                product.setImageList(images);
            }
        }

        return products;
    }

    @Override
    public ProductModel getProductById(int productId) throws IOException {

        ProductModel product = this.productRepository.getById(productId);

        if (product != null) {
            List<String> images = this.getImagesByPaths(product.getImageList());
            product.setImageList(images);
        }

        return product;
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

        if (oldProduct == null) {
            throw new InvalidOperationException("Produto não encontrado!");
        }
        product.setCreationDate(oldProduct.getCreationDate());
        product.setLastUpdate(LocalDateTime.now());
        product.setActive(oldProduct.isActive());

        this.productRepository.update(product);
    }

    @Override
    public void deleteProduct(int productId) throws InvalidOperationException {

        // TODO product verification

        if (!this.productRepository.delete(productId)) {
            throw new InvalidOperationException("Produto não encontrado!");
        }
    }

    private List<String> getImagesByPaths(List<String> paths) throws IOException {

        ArrayList<String> images = new ArrayList<>();

        for (String path : paths) {
            images.add("data:image;base64, " + this.fileService.getImageBase64(path));
        }

        return images;
    }
}
