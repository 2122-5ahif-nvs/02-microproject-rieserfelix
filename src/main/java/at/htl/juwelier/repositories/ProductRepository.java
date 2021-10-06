package at.htl.juwelier.repositories;

import at.htl.juwelier.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    @Transactional
    public void create(Product product) {
      persist(product);
    }

    public Product findProduct(Long sn){
        return findById(sn);
    }

    public List<Product> findAllProducts(){
        return listAll();
    }

    @Transactional
    public Product update(Long sn, Product product){
       Product newProduct = findById(sn);

        newProduct.setIsNew(product.getIsNew());
        newProduct.setPrice(product.getPrice());
        newProduct.setYear(product.getYear());
        newProduct.setTitle(product.getTitle());

        return newProduct;
    }

    @Transactional
    public void delete(Long sn) {
        deleteById(sn);
    }
}
