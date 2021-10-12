package at.htl.juwelier.boundary;

import at.htl.juwelier.repositories.CustomerRepository;
import at.htl.juwelier.entity.Customer;
import at.htl.juwelier.entity.Product;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Customer API",
                description = " Managing all customer actions",
                version = "1.0",
                contact = @Contact(name = "rieserfelix", email = "htl.rieserfelix@gmail.com")
        ),
        tags = {
                @Tag(name = "api", description = "Public API"),
        }
)

@Path("api")
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;

    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "creates a Customer",
            description = "creates a customer and gives a response, if everything was ok"
    )
    public Response create(Customer customer) {
        customerRepository.addCustomer(customer);
        return Response.ok(customer, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("customer")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(
            summary = "all Customers in a list",
            description = "returns all Customer in a List"
    )
    public List<Customer> findAllCustomer() {
        return customerRepository.getAllCustomers();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(
            summary = "finds a Customer by the id",
            description = "a customer with id XY will be returned"
    )
    public Customer findCustomerById(@QueryParam("id") Long id) {
        return customerRepository.getCustomerById(id);
    }

    @GET
    @Path("customer")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(
            summary = "JeweleryCollection in a list",
            description = "returns all Jewelery from the JeweleryCollection in a List"
    )
    public List<Product> getJeweleryCollection() {
        return customerRepository.getJeweleryCollection();
    }
}