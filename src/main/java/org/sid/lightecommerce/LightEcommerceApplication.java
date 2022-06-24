package org.sid.lightecommerce;

import net.bytebuddy.utility.RandomString;
import org.sid.lightecommerce.dao.CategoryRepository;
import org.sid.lightecommerce.dao.ProductRepository;
import org.sid.lightecommerce.entities.Category;
import org.sid.lightecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class LightEcommerceApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(LightEcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
        categoryRepository.save(new Category(null,"Poussette et Baade",null,null,null));
        categoryRepository.save(new Category(null,"Siege Auto",null,null,null));
        categoryRepository.save(new Category(null,"jeux d'eveil",null,null,null));
        categoryRepository.save(new Category(null,"jeux & jouets",null,null,null));
        Random rnd = new Random();
        categoryRepository.findAll().forEach(c->{
            for (int i = 0; i < 10 ; i++){
                Product p = new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(1000));
                p.setAvailabe(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setCategory(c);
                p.setPhotoName("unknown.png");
                productRepository.save(p);
            }


        });
    }
}
