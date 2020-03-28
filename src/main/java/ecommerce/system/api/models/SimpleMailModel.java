package ecommerce.system.api.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SimpleMailModel {

    @NotNull
    @Size(min = 7, max = 200)
    private String to;

    @NotNull
    private String subject;

    @NotNull
    private String text;

    public SimpleMailModel(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
