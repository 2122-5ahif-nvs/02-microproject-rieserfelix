package at.htl.juwelier;

import at.htl.juwelier.entity.Invoice;
import at.htl.juwelier.entity.Customer;
import at.htl.juwelier.entity.Product;
import at.htl.juwelier.repositories.CustomerRepository;
import at.htl.juwelier.repositories.InvoiceRepository;
import at.htl.juwelier.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
public class InvoiceRepositoryTest {

    InvoiceRepository invoiceRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
        invoiceRepository = new InvoiceRepository();
        customerRepository = new CustomerRepository();
        productRepository = new ProductRepository();
    }

    @Test
    void testRepoIsEmptyOnStart() {
        assertThat(invoiceRepository).isNotNull();
        assertThat(invoiceRepository.getAllInvoices().size()).isEqualTo(0);
    }

    @Test
    void testAddInvoice() {
        LocalDate testDate = LocalDate.now();

        Customer newC = new Customer();
        newC.setFirstName("Felix");
        newC.setLastName("Rieser");

        newC.setBirthDate(testDate);
        customerRepository.addCustomer(newC);

        Product product = new Product();
        product.setSerialNo(1);
        product.setPrice(2000);
        product.setIsNew(true);
        product.setTitle("Chain");
        product.setYear(2020);
        productRepository.create(product);

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(testDate);
        invoice.setPrice(product.getPrice());
        invoice.setCustomerId(newC.getId());
        invoice.setJewelerySerialNo(product.getSerialNo());

        assertThat(invoiceRepository.getInvoiceByCustomer(newC).size()).isEqualTo(0);

        invoiceRepository.addInvoice(invoice);

        assertThat(invoiceRepository.getInvoiceByCustomer(newC).size()).isEqualTo(1);
        assertThat(invoiceRepository.getInvoiceByCustomer(newC).get(0).getPrice()).isEqualTo(2000);
        assertThat(invoiceRepository.getInvoiceByCustomer(newC).get(0).getCustomer().getId()).isEqualTo(69);
    }

}
