package webproject.model.Interfaces;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document implements Printed {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private final int number;
    @Getter
    @Setter
    private String name;

    protected Document() {
        this.number = 0;
    }

    public Document(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String getPrintedText() {
        return this.getNumberWithName();
    }

    @Override
    public String getNumberWithName() {
        return String.format("Номер документа: %1$s\n", number) +
                String.format("Наименование документа: %1$s\n", name);
    }
}
