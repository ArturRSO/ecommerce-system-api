package ecommerce.system.api.tools;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAEncoder {

    public String encode(String text) throws NoSuchAlgorithmException {

        String salt = "Art01";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);

            if (hex.length() == 1) {
                stringBuilder.append('0');
            }

            stringBuilder.append(hex);
        }

        return stringBuilder.toString();
    }
}
