package ecommerce.system.api.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class FileHandler {

    public void saveMultipartFile(MultipartFile file, String path) throws IOException {

        File folder = new File(path);

        file.transferTo(folder);
    }

    public byte[] getImageBytes(String path) throws IOException {

        String extension = path.split("\\.(?=[^.]*$)")[1];

        BufferedImage image = ImageIO.read(new File(path));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, extension, outputStream);

        return outputStream.toByteArray();
    }
}
