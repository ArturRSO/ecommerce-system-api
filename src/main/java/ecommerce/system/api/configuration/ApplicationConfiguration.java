package ecommerce.system.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationConfiguration {

    // Base Urls
    private String baseUrlApi;
    private String baseUrlFront;

    // Image paths
    private String imagePathProducts;
    private String imagePathStores;
    private String imagePathUsers;
    private String imagePathProductsDefault;
    private String imagePathStoresDefault;
    private String imagePathUsersDefault;

    public String getBaseUrlApi() {
        return baseUrlApi;
    }

    public void setBaseUrlApi(String baseUrlApi) {
        this.baseUrlApi = baseUrlApi;
    }

    public String getBaseUrlFront() {
        return baseUrlFront;
    }

    public void setBaseUrlFront(String baseUrlFront) {
        this.baseUrlFront = baseUrlFront;
    }

    public String getImagePathProducts() {
        return imagePathProducts;
    }

    public void setImagePathProducts(String imagePathProducts) {
        this.imagePathProducts = imagePathProducts;
    }

    public String getImagePathStores() {
        return imagePathStores;
    }

    public void setImagePathStores(String imagePathStores) {
        this.imagePathStores = imagePathStores;
    }

    public String getImagePathUsers() {
        return imagePathUsers;
    }

    public void setImagePathUsers(String imagePathUsers) {
        this.imagePathUsers = imagePathUsers;
    }

    public String getImagePathProductsDefault() {
        return imagePathProductsDefault;
    }

    public void setImagePathProductsDefault(String imagePathProductsDefault) {
        this.imagePathProductsDefault = imagePathProductsDefault;
    }

    public String getImagePathStoresDefault() {
        return imagePathStoresDefault;
    }

    public void setImagePathStoresDefault(String imagePathStoresDefault) {
        this.imagePathStoresDefault = imagePathStoresDefault;
    }

    public String getImagePathUsersDefault() {
        return imagePathUsersDefault;
    }

    public void setImagePathUsersDefault(String imagePathUsersDefault) {
        this.imagePathUsersDefault = imagePathUsersDefault;
    }
}
