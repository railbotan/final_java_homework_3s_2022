package webproject.model.Classes;

import webproject.model.Interfaces.PersonnelDocument;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dismissal_orders")
public class DismissalOrder extends PersonnelDocument {
    static private int count = 0;

    @Getter
    @Setter
    @Column(name = "reason_dismissal")
    private String reasonDismissal;

    public DismissalOrder() {
        super(count, null, null, null);
    }

    public DismissalOrder(String name, String employee, String orderText, String reasonDismissal) {
        super(count++, name, employee, orderText);
        this.reasonDismissal = reasonDismissal;
    }

    @Override
    public String getPrintedText() {
        return super.getPrintedText() + String.format("Причина уволнения: %1$s\n", reasonDismissal);
    }
}
