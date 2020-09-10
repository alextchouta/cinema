package org.sid.inventoryservice;

import org.sid.inventoryservice.model.Product;
import org.sid.inventoryservice.reposotory.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration)
    {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null, "Ord Hp",8700));
            productRepository.save(new Product(null, "ord Dell",7400));
            productRepository.save(new Product(null, "Ord Mac",9500));
        };
    }

}
