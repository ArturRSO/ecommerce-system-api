package ecommerce.system.api.tools;

import ecommerce.system.api.enums.NotificationsEnum;
import ecommerce.system.api.exceptions.InvalidOperationException;
import ecommerce.system.api.models.SimpleMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class NotificationHandler {

    @Value("${application.base-url-front}")
    private String baseUrl;

    @Value("${token.password-recover-expiration}")
    private String passwordRecoverExpiration;

    @Value("${token.password-recover-key}")
    private String passwordRecoverKey;

    private final AESCodec aesCodec;
    private final EmailSender emailSender;

    @Autowired
    public NotificationHandler(AESCodec aesCodec, EmailSender emailSender) {
        this.aesCodec = aesCodec;
        this.emailSender = emailSender;
    }

    private String createTokenBase(int userId, int nofificationId, int expiration, String data) {

        if (data == null) {
            data = "empty";
        }

        int seconds = expiration / 1000;

        return nofificationId + "|" + LocalDateTime.now().plusSeconds(seconds) + "|" + userId + "|" + data;
    }

    private Map<String, String> getTokenInfoForNotification(NotificationsEnum notification) {

        Map<String, String> tokenInfo = new HashMap<>();

        switch (notification) {
            case PASSWORD_RECOVER:
                tokenInfo.put("expiration", this.passwordRecoverExpiration);
                tokenInfo.put("key", this.passwordRecoverKey);
                break;
            default:
                return null;
        }

        return tokenInfo;
    }

    private String generateLink(int userId, NotificationsEnum notification, String data) throws Exception {

        if (notification.isTokenNeeded()) {

            Map<String, String> tokenInfo = this.getTokenInfoForNotification(notification);

            if (tokenInfo == null) {
                throw new InvalidOperationException("Notificação desconhecida!");
            }

            String base = this.createTokenBase(userId, notification.getId(),
                    Integer.parseInt(tokenInfo.get("expiration")), data);
            String encryptedParameter = this.aesCodec.encryptText(base, tokenInfo.get("key"));

            return baseUrl + notification.getRoute() + UriUtils.encode(encryptedParameter, "UTF-8");
        }

        return baseUrl + notification.getRoute();
    }

    private String[] breakToken(String token, NotificationsEnum notification) throws Exception {

        Map<String, String> tokenInfo = this.getTokenInfoForNotification(notification);

        if (tokenInfo == null) {
            throw new InvalidOperationException("Notificação desconhecida!");
        }

        String decryptedToken = this.aesCodec.decryptText(token, tokenInfo.get("key"));

        return decryptedToken.split(Pattern.quote("|"), 4);
    }

    private LocalDateTime extractExpirationDate(String token, NotificationsEnum notification) throws Exception {

        String[] splitedToken = this.breakToken(token, notification);

        return LocalDateTime.parse(splitedToken[1]);
    }

    private int extractNotificationId(String token, NotificationsEnum notification) throws Exception {

        String[] splitedToken = this.breakToken(token, notification);

        return Integer.parseInt(splitedToken[0]);
    }

    public int extractUserId(String token, NotificationsEnum notification) throws Exception {

        String[] splitedToken = this.breakToken(token, notification);

        return Integer.parseInt(splitedToken[2]);
    }

    public boolean validateToken(String token, NotificationsEnum notification) throws Exception {

        LocalDateTime expirationDate = this.extractExpirationDate(UriUtils.decode(token, "UTF-8"), notification);
        int notificationId = this.extractNotificationId(UriUtils.decode(token, "UTF-8"), notification);

        return LocalDateTime.now().isBefore(expirationDate) && notificationId == notification.getId();
    }

    public SimpleMail sendEmail(int userId, String userEmail, NotificationsEnum notification,
            Map<String, String> data) throws Exception {

        String tokenData = data == null ? null : data.get("token");

        String link = this.generateLink(userId, notification, tokenData);

        String emailTemplate = notification.getMessageTemplate();

        emailTemplate = emailTemplate.replace("[[link]]", link);

        if (notification.getTemplateVariables() != null && data != null) {
            for (String variable : notification.getTemplateVariables()) {
                emailTemplate = emailTemplate.replace(variable, data.get(variable));
            }
        }

        SimpleMail mail = new SimpleMail(userEmail, notification.getSubject(), emailTemplate);

        this.emailSender.sendMimeEmail(mail);

        return mail;
    }
}
