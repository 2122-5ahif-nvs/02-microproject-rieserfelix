package at.htl.juwelier.boundary;

import at.htl.juwelier.repositories.ProductRepository;
import at.htl.juwelier.entity.Product;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Path("api")
public class ProductService {


    @Inject
    ProductRepository repo;

    public ProductService() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("product")
    public Response addProduct(Product product) {
        repo.create(product);

        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("product/{serialNo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Product findProduct(@PathParam("serialNo") Long sn){
        return repo.findProduct(sn);
    }

    @GET
    @Path("product")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Product> findAllProducts(){
        return repo.findAllProducts();
    }

    @PUT
    @Path("product/{serialNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("serialNo") Long sn, Product product){
        repo.update(sn, product);

        return Response.ok(product, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("product/{serialNo}")
    public Response delete(@PathParam("serialNo") Long sn){

        repo.delete(sn);

        return Response.noContent().build();
    }


}
