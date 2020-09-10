package org.sid.billingservice;

import org.sid.billingservice.model.Bill;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.model.ProductItem;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.service.CustomerService;
import org.sid.billingservice.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            CustomerService customerService, InventoryService inventoryService)
    {
        return args -> {

            Customer c1 = customerService.findCustomerById(1L);
            System.out.println(c1.getId() + " " + c1.getName() + " " + c1.getEmail());
            Bill bill1 = billRepository.save(new Bill(null,new Date(),c1.getId(),null,null ));

/*            Product p1 = inventoryService.findProductById(1L);
            Product p2 = inventoryService.findProductById(2L);
            Product p3 = inventoryService.findProductById(3L);*/

            inventoryService.findAllProducts().getContent().forEach(product -> {
                System.out.println(product.getName() + " " +product.getPrice());
                productItemRepository.save(new ProductItem(null, product.getId(),null,product.getPrice(),30,bill1));
            });
/*            productItemRepository.save(new ProductItem(null,p1.getId(),p1.getPrice(),30,bill1));
            productItemRepository.save(new ProductItem(null,p2.getId(),p2.getPrice(),15,bill1));
            productItemRepository.save(new ProductItem(null,p3.getId(),p3.getPrice(),40,bill1));*/
        };
    }

}
