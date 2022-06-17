package webproject.model.Classes;

import webproject.model.Interfaces.Document;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mails")
public class Mail extends Document {
    static private int count = 0;
    @Getter
    @Setter
    @Column(name = "sender")
    private String from;
    @Getter
    @Setter
    @Column(name = "receiver")
    private String to;
    @Getter
    @Setter
    @Column(name = "message_text")
    private String message;

    public Mail() {
        super(count, null);
    }

    public Mail(String name, String from, String to, String message) {
        super(count++, name);
        this.from = from;
        this.to = to;
        this.message = message;
    }

    @Override
    public String getPrintedText() {
        return super.getPrintedText() +
                String.format("Отправитель: %1$s\n", from) +
                String.format("Адресат: %1$s\n", to) +
                String.format("Сообщение: %1$s\n", message);
    }
}
