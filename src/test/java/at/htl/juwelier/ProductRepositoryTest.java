package at.htl.juwelier;

import at.htl.juwelier.entity.Product;
import at.htl.juwelier.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class ProductRepositoryTest {

    ProductRepository productRepository;


    @BeforeEach
    void beforeEach() {
        productRepository = new ProductRepository();
    }

    @Test
    void testRepoIsEmptyOnStart() {
        assertThat(productRepository).isNotNull();
        assertThat(productRepository.findAllProducts().size()).isEqualTo(0);
    }

    @Test
    void testAddProduct() {
        assertThat(productRepository.findProduct((long)1)).isNull();

        Product product = new Product();
        product.setSerialNo(1);
        product.setPrice(2000);
        product.setIsNew(true);
        product.setTitle("Chain");
        product.setYear(2020);

        productRepository.create(product);

        Product newProduct = productRepository.findProduct((long)1);

        assertThat(newProduct).isNotNull();
        assertThat(newProduct.getSerialNo()).isEqualTo(1);
        assertThat(newProduct.getPrice()).isEqualTo(2000);
        assertThat(newProduct.getIsNew()).isEqualTo(true);
        assertThat(newProduct.getTitle()).isEqualTo("Chain");
        assertThat(newProduct.getYear()).isEqualTo(2020);
    }

    @Test
    void testRemoveProduct() {
        assertThat(productRepository.findProduct((long)1)).isNull();

        Product product = new Product();
        product.setSerialNo(1);
        product.setPrice(2000);
        product.setIsNew(true);
        product.setTitle("Chain");
        product.setYear(2020);
        productRepository.create(product);

        assertThat(productRepository.findProduct((long)1)).isNotNull();

        productRepository.delete((long)1);

        assertThat(productRepository.findProduct((long)1)).isNull();
    }

    @Test
    void testUpdateProduct() {

        assertThat(productRepository.findProduct((long)1)).isNull();

        Product product = new Product();
        product.setSerialNo(1);
        product.setPrice(2000);
        product.setIsNew(true);
        product.setTitle("Chain");
        product.setYear(2020);
        productRepository.create(product);

        assertThat(productRepository.findProduct((long)1).getTitle()).isEqualTo("Chain");

        Product newJ = new Product();
        newJ.setSerialNo(1);
        newJ.setTitle("Icedout Chain");

        productRepository.update((long)1, newJ);

        assertThat(productRepository.findProduct((long)1).getTitle()).isEqualTo("Icedout Chain");

    }
}
