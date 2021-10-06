package at.htl.juwelier.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jewelerySerialNo;

    @ManyToOne
    private Customer customer;
    private Double price;
    private LocalDate invoiceDate;

    public Invoice() {
    }

    public long getJewelerySerialNo() { return jewelerySerialNo; }

    public Customer getCustomer() {
        return customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getBillDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public void setJewelerySerialNo(long jewelerySerialNo) { this.jewelerySerialNo = jewelerySerialNo; }

    public void setCustomerId(long customerId) { this.customer = customer; }
}
