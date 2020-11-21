package ecommerce.system.api.services.implementations;

import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.services.IFileService;
import ecommerce.system.api.tools.FileHandler;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileService implements IFileService {

    @Value("${application.image-path-products}")
    private String productImagePath;

    @Value("${application.image-path-stores}")
    private String storeImagePath;

    @Value("${application.image-path-users}")
    private String userImagePath;

    private final FileHandler fileHandler;

    @Autowired
    public FileService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public String saveMultpartImage(MultipartFile file, String object, int id) throws InvalidOperationException, IOException {

        String path;

        switch (object) {
            case "product":
                path = this.productImagePath;
                break;
            case "store":
                path = this.storeImagePath;
                break;
            case "user":
                path = this.userImagePath;
                break;
            default:
                throw new InvalidOperationException("Objeto desconhecido!");
        }

        String[] splittedName = file.getOriginalFilename().split("\\.");
        String extension = splittedName[splittedName.length - 1];

        path += id + "." + extension;

        this.fileHandler.saveMultipartFile(file, path);

        return path;
    }

    @Override
    public String getImageBase64(String path) throws IOException {

        byte[] bytes = this.fileHandler.getImageBytes(path);

        return new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
    }
}
