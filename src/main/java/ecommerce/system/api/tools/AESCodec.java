package ecommerce.system.api.tools;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AESCodec {

    private SecretKeySpec setKey(String key) throws NoSuchAlgorithmException {

        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        keyBytes = messageDigest.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);

        return new SecretKeySpec(keyBytes, "AES");
    }

    public String encryptText(String text, String key)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {

        SecretKeySpec secretKey = this.setKey(key);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));

        return encryptedText;
    }

    public String decryptText(String text, String key)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {

        SecretKeySpec secretKey = this.setKey(key);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        String decryptedText = new String(cipher.doFinal(Base64.getMimeDecoder().decode(text)));

        return decryptedText;
    }
}
