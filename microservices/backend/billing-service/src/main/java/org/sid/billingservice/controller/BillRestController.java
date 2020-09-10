package org.sid.billingservice.controller;

import lombok.AllArgsConstructor;
import org.sid.billingservice.model.Bill;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.service.CustomerService;
import org.sid.billingservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerService customerService;
    private InventoryService inventoryService;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id)
    {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));

        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryService.findProductById(productItem.getProductID()));
        });
        return bill;
    }
}
