package ecommerce.system.api.services.implementations;

import ecommerce.system.api.enums.MessagesEnum;
import ecommerce.system.api.enums.OrderStatusEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.*;
import ecommerce.system.api.repositories.IProductRepository;
import ecommerce.system.api.repositories.IProductSubtypeRepository;
import ecommerce.system.api.repositories.IProductTypeRepository;
import ecommerce.system.api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IAuthenticationService authenticationService;
    private final IFileService fileService;
    private final IOrderService orderService;
    private final IProductRepository productRepository;
    private final IProductTypeRepository productTypeRepository;
    private final IProductSubtypeRepository productSubtypeRepository;
    private final IStoreService storeService;
    private final IUserService userService;

    @Autowired
    public ProductService(
            IAuthenticationService authenticationService,
            IFileService fileService,
            @Lazy IOrderService orderService,
            IProductRepository productRepository,
            IProductTypeRepository productTypeRepository,
            IProductSubtypeRepository productSubtypeRepository,
            IStoreService storeService,
            IUserService userService) {
        this.authenticationService = authenticationService;
        this.fileService = fileService;
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.productSubtypeRepository = productSubtypeRepository;
        this.storeService = storeService;
        this.userService = userService;
    }

    @Override
    public int createProduct(Product product) {

        product.setCreationDate(LocalDateTime.now());
        product.setLastUpdate(null);
        product.setActive(true);

        int productId = this.productRepository.createProduct(product);

        for (ProductDetail detail : product.getDetails()) {
            this.productRepository.createProductDetail(detail, productId);
        }

        return productId;
    }

    @Override
    public int createProductImage(MultipartFile file, int productId) throws InvalidOperationException, IOException {

        Product product = this.productRepository.getProductById(productId);

        if (product == null) {
            throw new InvalidOperationException("Produto não encontrado!");
        }

        if (product.getImages() != null && product.getImages().size() > 0) {
            if (product.getImages().size() >= 4) {
                throw new InvalidOperationException("Este produto já tem o máximo de imagens permitidas cadastradas!");
            }
        }

        String imagePath = this.fileService.saveMultpartImage(file, "product", productId);

        return this.productRepository.createProductImage(productId, imagePath);
    }

    @Override
    public List<Product> getProductsByQuantity(int quantity) throws IOException {

        List<Product> products = this.productRepository.getProductsByQuantity(quantity);

        if (products != null) {
            for (Product product : products) {
                List<ProductImage> images = this.getImagesByPaths(product.getImages());
                product.setImages(images);
            }
        }

        return products;
    }

    @Override
    public List<Product> getProductsByNameAndQuantity(String name, int quantity) throws IOException {

        List<Product> products = this.productRepository.getProductsByNameAndQuantity(name, quantity);

        if (products != null) {
            for (Product product : products) {
                List<ProductImage> images = this.getImagesByPaths(product.getImages());
                product.setImages(images);
            }
        }

        return products;
    }

    @Override
    public List<Product> getProductsByStoreIdAndQuantity(int storeId, int quantity) throws IOException {

        List<Product> products = this.productRepository.getProductsByStoreIdAndQuantity(storeId, quantity);

        if (products != null) {
            for (Product product : products) {
                List<ProductImage> images = this.getImagesByPaths(product.getImages());
                product.setImages(images);
            }
        }

        return products;
    }

    @Override
    public List<Product> getProductsBySubtypeIdAndQuantity(int subtypeId, int quantity) throws IOException {

        List<Product> products = this.productRepository.getProductsBySubtypeIdAndQuantity(subtypeId, quantity);

        if (products != null) {
            for (Product product : products) {
                List<ProductImage> images = this.getImagesByPaths(product.getImages());
                product.setImages(images);
            }
        }

        return products;
    }

    @Override
    public Product getProductById(int productId) throws IOException {

        Product product = this.productRepository.getProductById(productId);

        if (product != null) {
            List<ProductImage> images = this.getImagesByPaths(product.getImages());
            product.setImages(images);
        }

        return product;
    }

    @Override
    public List<ProductType> getAllProductTypes() {

        return this.productTypeRepository.getAll();
    }

    @Override
    public List<ProductSubtype> getProductSubtypesByProductTypeId(int productTypeId) {

        return this.productSubtypeRepository.getByProductTypeId(productTypeId);
    }

    @Override
    public List<ProductDetail> getProductDetailLabelsByProductSubtypeId(int productSubtypeId) {
        return this.productRepository.getProductDetailLabelsByProductSubtypeId(productSubtypeId);
    }

    @Override
    public void updateProduct(Product product, boolean systemUpdate)
            throws InvalidOperationException, IOException {

        Product oldProduct = this.productRepository.getProductById(product.getProductId());

        if (oldProduct == null) {
            throw new InvalidOperationException("Produto não encontrado!");
        }

        Store store = this.storeService.getStoreByProductId(product.getProductId());

        List<User> users = this.userService.getUsersByStoreId(store.getStoreId());

        if (users.stream().noneMatch(user -> this.authenticationService.isLoggedUser(user.getUserId()))
                && !systemUpdate) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        product.setCreationDate(oldProduct.getCreationDate());
        product.setLastUpdate(LocalDateTime.now());
        product.setActive(oldProduct.isActive());

        for (ProductDetail detail : product.getDetails()) {
            if (!this.productRepository.updateProductDetails(detail, product.getProductId())) {
                throw new InvalidOperationException("Erro ao atualizar detalhes do produto!");
            }
        }

        this.productRepository.updateProduct(product);
    }

    @Override
    public void updateProductImage(MultipartFile file, int productId, int imageId)
            throws InvalidOperationException, IOException {

        Product product = this.productRepository.getProductById(productId);

        if (product == null) {
            throw new InvalidOperationException("Produto não encontrado!");
        }

        String imagePath = this.fileService.saveMultpartImage(file, "product", productId);

        this.productRepository.updateProductImage(new ProductImage(productId, imagePath));
    }

    @Override
    public void deleteProduct(int productId) throws InvalidOperationException, IOException {

        Product product = this.getProductById(productId);

        if (product == null) {
            throw new InvalidOperationException("Produto não encontrado.");
        }

        Store store = this.storeService.getStoreByProductId(productId);

        List<User> users = this.userService.getUsersByStoreId(store.getStoreId());

        if (users.stream().noneMatch(user -> this.authenticationService.isLoggedUser(user.getUserId()))) {
            throw new InvalidOperationException(MessagesEnum.UNALLOWED.getMessage());
        }

        List<Order> orders = this.orderService.getOrdersByProductId(productId);

        for (Order order : orders) {

            if (order.getOrderStatusId() != OrderStatusEnum.FINISHED.getId()) {
                throw new InvalidOperationException(
                        "Não é possível desativar um produto com pedidos em aberto. Caso queira removê-lo da loja antes, mude o quantidade em estoque para zero.");
            }
        }

        this.productRepository.deleteProduct(productId);
    }

    private List<ProductImage> getImagesByPaths(List<ProductImage> images) throws IOException {

        for (ProductImage image : images) {
            image.setImage("data:image;base64, " + this.fileService.getImageBase64(image.getPath()));
        }

        return images;
    }
}
