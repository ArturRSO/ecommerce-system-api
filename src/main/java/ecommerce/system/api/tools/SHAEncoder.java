package ecommerce.system.api.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAEncoder {

    @Value("${application.hash.salt}")
    private String salt;

    public String encode(String text) throws NoSuchAlgorithmException {

        String salt = "Art01";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1) {
                stringBuilder.append('0');
            }

            stringBuilder.append(hex);
        }

        return stringBuilder.toString();
    }
}
