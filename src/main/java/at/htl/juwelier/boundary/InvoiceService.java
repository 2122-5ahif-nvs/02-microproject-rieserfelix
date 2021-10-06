package at.htl.juwelier.boundary;

import at.htl.juwelier.repositories.InvoiceRepository;
import at.htl.juwelier.repositories.CustomerRepository;
import at.htl.juwelier.entity.Invoice;
import at.htl.juwelier.entity.Customer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api")
public class InvoiceService {

    @Inject
    InvoiceRepository invoiceRepository;

    @Inject
    CustomerRepository customerRepository;

    @POST
    @Path("invoice")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Invoice invoice) {
        invoiceRepository.addInvoice(invoice);
        return Response.ok(invoice, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("invoice")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Invoice> findAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    @GET
    @Path("invoice/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Invoice> getCurrentInvoicesByCustomer(@PathParam("id") Long id) {
        Customer customer = customerRepository.getCustomerById(id);
        return invoiceRepository.getInvoiceByCustomer(customer);
    }
}
