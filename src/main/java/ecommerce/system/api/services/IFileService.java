package ecommerce.system.api.services;

import ecommerce.system.api.exceptions.InvalidOperationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String saveMultpartImage(MultipartFile file, String object, int id) throws InvalidOperationException, IOException;
    String getImageBase64(String path) throws IOException;
}
