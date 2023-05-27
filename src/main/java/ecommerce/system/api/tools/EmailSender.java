package ecommerce.system.api.tools;

import ecommerce.system.api.models.SimpleMailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;

    @Autowired
    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMail(SimpleMailModel mail) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());

        this.emailSender.send(message);
    }

    public void sendMimeEmail(SimpleMailModel mail) throws MessagingException {

        MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom(from);
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getText(), true);

        this.emailSender.send(mimeMessage);
    }
}
