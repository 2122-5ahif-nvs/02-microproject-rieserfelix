package at.htl.juwelier.repositories;

import at.htl.juwelier.entity.Invoice;
import at.htl.juwelier.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<Invoice> {

    public List<Invoice> getAllInvoices() {
        return listAll();
    }

    public List<Invoice> getInvoiceByCustomer(Customer customer) {
        return listAll().stream().filter(invoice -> invoice.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    @Transactional
    public void addInvoice(Invoice invoice) {
        persist((invoice));
    }
}
