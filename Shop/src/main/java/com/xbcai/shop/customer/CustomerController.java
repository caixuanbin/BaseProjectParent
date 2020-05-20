package com.xbcai.shop.customer;

import com.inter.CustomerRepository;
import com.xbcai.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName){
        Customer customer = new Customer(firstName,lastName);
        return customerRepository.save(customer);
    }

    @PutMapping("/editCustomer/{id}")
    public Customer editCustomer(@PathVariable("id") Long id,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName){
        Customer customer = new Customer(firstName,lastName);
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        return (List<Customer>) customerRepository.findAll();
    }

    @DeleteMapping("/delCustomer/{id}")
    public void delCustomer(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
    }

}
