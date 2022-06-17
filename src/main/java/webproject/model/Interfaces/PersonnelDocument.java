package webproject.model.Interfaces;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personnel_documents")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonnelDocument extends Document {
    @Getter
    @Setter
    private String employee;
    @Getter
    @Setter
    @Column(name = "order_text")
    private String orderText;
    @Getter
    private Status status;

    protected PersonnelDocument() {
        super();
        this.employee = null;
    }

    public PersonnelDocument(int number, String name, String employee, String orderText) {
        super(number, name);
        this.employee = employee;
        this.orderText = orderText;
        this.status = Status.CREATED;
    }

    public void execute() {
        this.status = Status.EXECUTED;
    }

    @Override
    public String getPrintedText() {
        return super.getPrintedText() +
                String.format("Статус: %1$s\n", status.getValue()) +
                String.format("Сотрудник: %1$s\n", employee) +
                String.format("Приказ: %1$s\n", orderText);
    }
}
