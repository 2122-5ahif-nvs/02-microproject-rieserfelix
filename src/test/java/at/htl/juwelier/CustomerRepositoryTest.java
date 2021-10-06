package at.htl.juwelier;

import at.htl.juwelier.entity.Customer;
import at.htl.juwelier.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryTest {


    CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        customerRepository = new CustomerRepository();
    }

    @Test
    void testRepoIsEmptyOnStart() {
        assertThat(customerRepository).isNotNull();
        assertThat(customerRepository.getAllCustomers().size()).isEqualTo(0);
    }

    @Test
    void testAddCustomer() {
        assertThat(customerRepository.findById(69L)).isNull();

        Customer newC = new Customer();
        newC.setFirstName("Felix");
        newC.setLastName("Rieser");

        LocalDate birthDate = LocalDate.now();

        newC.setBirthDate(birthDate);
        customerRepository.addCustomer(newC);


        Customer customer = customerRepository.findById(69L);

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(69);
        assertThat(customer.getFirstName()).isEqualTo("Felix");
        assertThat(customer.getLastName()).isEqualTo("Rieser");
        assertThat(customer.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    void tesdRemoveCustomer() {
        Customer newC = new Customer();
        newC.setFirstName("Felix");
        newC.setLastName("Rieser");

        LocalDate birthDate = LocalDate.now();

        newC.setBirthDate(birthDate);
        customerRepository.addCustomer(newC);


        assertThat(customerRepository.findById(69L)).isNotNull();

        customerRepository.removeCustomer(69L);

        assertThat(customerRepository.findById(69L)).isNull();
    }

    @Test
    void testUpdateCustomer(){

        Customer newC = new Customer();
        newC.setFirstName("Felix");
        newC.setLastName("Rieser");

        LocalDate birthDate = LocalDate.now();

        newC.setBirthDate(birthDate);
        customerRepository.addCustomer(newC);


        assertThat(customerRepository.findById(69L).getFirstName()).isEqualTo("Felix");

        Customer newUpdateC = customerRepository.findById(newC.getId());
        newUpdateC.setFirstName("Fieser");
        newUpdateC.setLastName("Relix");

        customerRepository.update(newUpdateC.getId(), newUpdateC);

        assertThat(customerRepository.findById(69L).getFirstName()).isEqualTo("Fieser");
    }
}