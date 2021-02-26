package org.kodluyoruz.mybank.contoller;

import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create/{name}/{surname}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createCustomer(@PathVariable String name, @PathVariable String surname){

        return customerService.createCustomer(name,surname);
    }

    @GetMapping("/listAll")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @DeleteMapping("/{id}")

    public  ResponseEntity<Object> deleteCustomer(@PathVariable long id){

        return customerService.deleteCustomer(id);
    }
    @PutMapping("/{id}/{name}/{surname}")

    public  ResponseEntity<Object> updateCustomer(@PathVariable long id, @PathVariable String name, @PathVariable  String surname){

        return customerService.updateCustomer(id,name,surname);
    }

}

