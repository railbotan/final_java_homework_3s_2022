package webproject.model.Classes;

import webproject.model.Interfaces.PersonnelDocument;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employment_orders")
public class EmploymentOrder extends PersonnelDocument {
    static private int count = 0;

    public EmploymentOrder() {
        super(count, null, null, null);
    }

    public EmploymentOrder(String name, String employee, String orderText) {
        super(count++, name, employee, orderText);
    }
}
