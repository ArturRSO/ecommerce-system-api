package ecommerce.system.api.tools;

import ecommerce.system.api.models.SimpleMailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMail(SimpleMailModel mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        this.emailSender.send(message);
    }

    public void sendMimeEmail(SimpleMailModel mail) throws MessagingException {

        MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(mail.getText(), true);
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());

        this.emailSender.send(mimeMessage);
    }
}
