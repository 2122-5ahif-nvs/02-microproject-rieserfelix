package at.htl.juwelier.repositories;


import at.htl.juwelier.entity.Customer;
import at.htl.juwelier.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void addCustomer(Customer newCustomer) {
       persist(newCustomer);
    }

    @Transactional
    public void removeCustomer(Long id) {
        deleteById(id);
    }

    @Transactional
    public  void update(Long id, Customer newC) {
       Customer oldC = findById(id);
       oldC.setFirstName(newC.getFirstName());
       oldC.setLastName(newC.getLastName());
       oldC.setBirthDate(newC.getBirthDate());
    }

    public List<Customer>  getAllCustomers()
    {
       return listAll();
    }

    public List<Product> getJeweleryCollection(){
        return productRepository.listAll();
    }

    public Customer getCustomerById(Long id) {
        return findById(id);
    }
}
